package com.example.nextdoormvvm.user.ui.apartments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.internal.Validator
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import com.example.nextdoormvvm.user.network.response.Apartment
import com.example.nextdoormvvm.user.network.response.ApartmentListResponse
import com.example.nextdoormvvm.user.network.response.City
import com.example.nextdoormvvm.user.network.response.State
import com.example.nextdoormvvm.user.ui.UserBaseFragment
import com.example.nextdoormvvm.user.ui.otp.VerifyOtpFragment
import com.example.nextdoormvvm.user.ui.registerbuyer.RegisterBuyerFragment
import kotlinx.android.synthetic.main.apartment_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.text.method.KeyListener as MethodKeyListener

class ApartmentListFragment : UserBaseFragment() {

    private lateinit var phoneNo: String
    private lateinit var filteredApartment: List<Apartment>
    private lateinit var searchApartmentAdapter: ArrayAdapter<Apartment>
    private lateinit var cityAdapter: ArrayAdapter<City>
    private lateinit var stateAdapter: ArrayAdapter<State>
    private var selectedApartment  : Apartment? = null

    private lateinit var viewModel: ApartmentListViewModel


    companion object {

        fun newInstance(value: Map<String, Any>): ApartmentListFragment {
            val thisFragment = ApartmentListFragment()
            val args = Bundle()
            args.putString(
                VerifyOtpFragment.KEY_MOBILE_NUMBER,
                value[VerifyOtpFragment.KEY_MOBILE_NUMBER].toString()
            )
            thisFragment.arguments = args
            return thisFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val args = arguments

        args?.let {
            phoneNo = it.getString(VerifyOtpFragment.KEY_MOBILE_NUMBER, "")
        }

        return inflater.inflate(R.layout.apartment_list_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getApartmentListApi()
    }

    private fun getApartmentListApi() {
        viewModel = UserModelGenerator.getModel(
            this,
            UserViewModelTypeEnum.ApartmentListViewModel,
            mapOf("" to "")
        ) as ApartmentListViewModel
        // Get from Network
        launch(Dispatchers.Main) {
            val apartmentListResponse = viewModel.apartmentList.await()
            apartmentListResponse.observe(viewLifecycleOwner, Observer {
                it.bindUI()
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun ApartmentListResponse.bindUI() {
        stateAdapter = ArrayAdapter( requireContext(), android.R.layout.simple_dropdown_item_1line,states)
        state_spinner.adapter = stateAdapter

        val cityList = cities.filter { city: City -> (state_spinner.selectedItem as State).StateId == city.StateId }
        cityAdapter = ArrayAdapter( requireContext(),android.R.layout.simple_dropdown_item_1line,cityList)
        city_spinner.adapter = cityAdapter


        filteredApartment =apartments.filter {apartment: Apartment -> (city_spinner.selectedItem as City).CityId == apartment.CityId }
        searchApartmentAdapter = ArrayAdapter(requireContext(), R.layout.auto_complete_row, R.id.text_view_list_item,filteredApartment)
        tv_auto_complete.setAdapter(searchApartmentAdapter)
        tv_auto_complete.threshold = 1 // Search will appear on first char onwards


        tv_no_apartment_found.visibility = View.GONE
        tv_add_new_apartment_activity_launch.visibility = View.GONE
        tv_auto_complete.tag = tv_auto_complete.keyListener


        state_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected( parent: AdapterView<*>?,  view: View?,  position: Int, id: Long )
            {
                cityAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    cities.filter { city: City -> (state_spinner.selectedItem as State).StateId == city.StateId })
                    city_spinner.adapter = cityAdapter
            }
        }


        city_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, p2: Int, p3: Long) {
                tv_auto_complete.setText("")
                tv_auto_complete.keyListener = tv_auto_complete.tag as MethodKeyListener

                filteredApartment = apartments.filter { apartment: Apartment -> (city_spinner.selectedItem as City).CityId == apartment.CityId }
                searchApartmentAdapter = ArrayAdapter(requireContext(), R.layout.auto_complete_row, R.id.text_view_list_item,filteredApartment)
                tv_auto_complete.setAdapter(searchApartmentAdapter)
                tv_auto_complete.threshold = 1 // Search will appear on first char onwards
            }
        }


        tv_auto_complete.setOnItemClickListener { adapterView, _, i, _ ->
            selectedApartment = adapterView.getItemAtPosition(i) as Apartment
            tv_auto_complete.keyListener = null
            tv_proceed_add_apartment_activity_launch.text = "PROCEED"
        }

        tv_auto_complete.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    if (tv_auto_complete.compoundDrawables[2] != null && event.rawX >= (tv_auto_complete.right - tv_auto_complete.compoundDrawables[2].bounds.width())) {
                        tv_auto_complete.setText("")
                        selectedApartment = null
                        tv_auto_complete.keyListener = tv_auto_complete.tag as MethodKeyListener
                        tv_proceed_add_apartment_activity_launch.text = "Add Apartment"
                    }
                }
            }
            v?.onTouchEvent(event) ?: true
        }


        tv_auto_complete.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.length > 1) {
                    if (!tv_auto_complete.isPopupShowing) {
                        tv_no_apartment_found.visibility = View.VISIBLE
                        tv_add_new_apartment_activity_launch.visibility = View.VISIBLE
                    }
                    tv_auto_complete.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.search,
                        0,
                        R.drawable.remove,
                        0
                    )
                } else {
                    tv_no_apartment_found.visibility = View.GONE
                    tv_add_new_apartment_activity_launch.visibility = View.GONE
                    tv_auto_complete.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search, 0, 0, 0)
                }
            }
        })


        // set On ClickListener for Get Started
        tv_proceed_add_apartment_activity_launch.setOnClickListener {
            val flatNumber = flat_number_activity_launch.text.toString()
            if (Validator.validateFlatNumber(flatNumber)) {
                if (selectedApartment != null) {
                    val map = mapOf(VerifyOtpFragment.KEY_MOBILE_NUMBER to phoneNo, RegisterBuyerFragment.APARTMENT to selectedApartment!!,RegisterBuyerFragment.FLAT_NUMBER to flatNumber )
                    gotoFragmentWithBundleData(RegisterBuyerFragment::class.java, activity, map )
                }else{
                    Utility.toastThis("please select your apartment")
                }
            }else{
                Utility.toastThis("please enter your flat number")
            }
        }

        tv_add_new_apartment_activity_launch.setOnClickListener {

        }

    }

}
