package com.example.nextdoormvvm.user.ui.registerbuyer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.common.ui.CommonActivity
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.internal.Validator
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import com.example.nextdoormvvm.user.network.post.PostBuyer
import com.example.nextdoormvvm.user.network.response.Apartment
import com.example.nextdoormvvm.user.ui.UserBaseFragment
import com.example.nextdoormvvm.user.ui.otp.VerifyOtpFragment
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.android.synthetic.main.register_buyer_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RegisterBuyerFragment : UserBaseFragment() {

    private lateinit var viewModel: RegisterBuyerViewModel
    private lateinit var rootView: View
    private var flatNumber: String?=null
    private var apartment: Apartment?=null
    private lateinit var phoneNo: String



    companion object {
        const val APARTMENT = "apartment"
        const val FLAT_NUMBER = "flat number"


        fun newInstance(value:Map<String,Any>): RegisterBuyerFragment {
            val thisFragment = RegisterBuyerFragment()
            val args = Bundle()
            args.putString(VerifyOtpFragment.KEY_MOBILE_NUMBER, value[VerifyOtpFragment.KEY_MOBILE_NUMBER].toString())
            args.putParcelable(APARTMENT,value[APARTMENT] as Apartment)
            args.putString(FLAT_NUMBER,value[FLAT_NUMBER].toString())
            thisFragment.arguments = args
            return thisFragment
        }
    }





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        val args = arguments

        args?.let {
            this.phoneNo = it.getString(VerifyOtpFragment.KEY_MOBILE_NUMBER, "")
            this.apartment = it.getParcelable(APARTMENT)
            flatNumber = it.getString(FLAT_NUMBER, "")
        }

        rootView =inflater.inflate(R.layout.register_buyer_fragment, container, false)
        return rootView
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        et_customer_mobile_num_reg.setText(phoneNo)
        tv_selected_apartment_name_reg.text=apartment?.ApartmentName
        et_customer_mobile_num_reg.isEnabled = false
        et_flat_no_of_customer_reg.setText(flatNumber)
        et_flat_no_of_customer_reg.isEnabled = false

        tv_reg.setOnClickListener {
            this.postNewBuyer()
        }
    }

    private fun validationDone(): Boolean {
        var msg= ""

        if (!Validator.isValidFullName(et_full_name_of_customer_reg.text.toString())) msg = "invalid name"
        else if (!Validator.isValidPhone(et_customer_mobile_num_reg.text.toString())) msg =  "invalid mobile number"
        else if (!Validator.isValidEmail(et_customer_email_reg.text.toString())) msg = "invalid email"
        else if (rg_gender_reg.checkedRadioButtonId == -1) msg = "Please select a gender"
        if (msg != "") {
            Utility.toastThis( msg )
            return false
        }
        return true
    }

    private fun createBuyerInfo(): PostBuyer {
        return PostBuyer(
            ApartmentId = apartment?.ApartmentId!!,
            Email = et_customer_email_reg.text.toString(),
            FlatNumber = et_flat_no_of_customer_reg.text.toString(),
            FullName = et_full_name_of_customer_reg.text.toString(),
            Gender =rootView.findViewById<RadioButton>(rg_gender_reg.checkedRadioButtonId).text.toString(),
            IsEmailVerified = false,
            IsMobileNumberVerified = true,
            ProfileImageUrl = "",
            UserTypeId = 1, MobileNumber ="91"+ et_customer_mobile_num_reg.text.toString()
        )

    }
    private fun postNewBuyer() {
        if (!validationDone()) return
        val postBuyer = createBuyerInfo()
        viewModel = UserModelGenerator.getModel(
            this,
            UserViewModelTypeEnum.RegisterBuyerViewModel,
            postBuyer
        ) as RegisterBuyerViewModel


        launch(Dispatchers.Main) {
            val result=viewModel.insertBuyer.await()
            if(result.Id!=0)
                activity?.startActivity(Intent(activity, CommonActivity::class.java))
        }
    }






}
