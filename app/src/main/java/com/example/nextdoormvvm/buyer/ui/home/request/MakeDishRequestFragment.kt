package com.example.nextdoormvvm.buyer.ui.home.request

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.buyer.network.post.Order
import com.example.nextdoormvvm.buyer.network.response.InactiveDish
import com.example.nextdoormvvm.buyer.ui.home.checkout.CheckOutFragment.Companion.CONSTANT_FIFTY
import com.example.nextdoormvvm.buyer.ui.home.checkout.CheckOutFragment.Companion.CONSTANT_HUNDRED
import com.example.nextdoormvvm.internal.Preference
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.internal.Utility
import kotlinx.android.synthetic.main.make_request_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.ceil

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class MakeDishRequestFragment : ScopedFragment() {

    private lateinit var viewModel: RequestDishViewModel
    private lateinit var dishInfo: InactiveDish
    private lateinit var dateToRequest:Calendar
    private  var totalDishPrice =0
    private  var totalPrice =0
    private var totalDeliveryCharges:Int=0
    private var totalPackingCharge:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.make_request_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         dishInfo = arguments?.getParcelable("dishInfo")!!
        textView47.text=dishInfo.DishName
        tv_delivery_time_request_detail.text=Utility.fromCaldarToTimeSlot(Calendar.getInstance())
        tv_delivery_time_request_detail.setOnClickListener {
            showDataTimePicker()
        }
        request_button_request_detail.setOnClickListener {
            postOrderByRequest()
        }
        setDateRequestDateInFuture()
        tv_quantity_detail.text="1"
        totalDeliveryCharges += dishInfo.DeliveryCharge
        totalPackingCharge += dishInfo.PackingCharge
        totalDishPrice += dishInfo.UnitPrice
        totalPrice += totalDishPrice + totalDeliveryCharges + totalPackingCharge
        setBill()

        setPlusminus()
    }

    private fun postOrderByRequest() {
        val checkOutCOD = creatCODOrdert()
        viewModel = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.RequestDishViewModel,
            checkOutCOD
        ) as RequestDishViewModel
        GlobalScope.launch(Dispatchers.Main) {
            val newDishResponse = viewModel.postOrderByRequest.await()
            val bundle = bundleOf("orderId" to newDishResponse.Id)
            findNavController().navigate(  R.id.action_makeRequestFragment_to_orderConfirmationFragment, bundle)

        }
    }

    private fun setBill() {
        item_total_request_detail.text=totalDishPrice.toString()
        tv_unit_price_request_detail.text=dishInfo.UnitPrice.toString()
        packing_total_request_detail.text=totalPackingCharge.toString()
        delivery_total_request_detail.text=totalDeliveryCharges.toString()
        order_total_request_detail.text=totalPrice.toString()
    }

    private fun setPlusminus() {

        tv_plus_detail.setOnClickListener {
            var count = tv_quantity_detail.text.toString().toInt()

            if (count < 99) {

                if (count > 0) {

                    totalDeliveryCharges += dishInfo.DeliveryCharge
                    totalPackingCharge += dishInfo.PackingCharge
                    totalDishPrice += dishInfo.UnitPrice
                    totalPrice=totalDishPrice+totalDeliveryCharges+totalPackingCharge

                }

                count++
                setBill()
                tv_quantity_detail.text = count.toString()
            }
        }


        tv_minus_detail.setOnClickListener {
            var count = tv_quantity_detail.text.toString().toInt()

            if (count > 1) {
                count--

                totalDeliveryCharges -= dishInfo.DeliveryCharge
                totalPackingCharge -= dishInfo.PackingCharge
                totalDishPrice -= dishInfo.UnitPrice
                totalPrice=totalDishPrice+totalDeliveryCharges+totalPackingCharge

                tv_quantity_detail.text = count.toString()
                setBill()
            }

        }
    }

    private fun showDataTimePicker(){
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(context,android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                setTime(
                    selectedHour,
                    selectedMinute
                )
            },
            hour,
            minute,
            false
        )
        mTimePicker.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
    }



    private fun setTime(selectedHour: Int, selectedMinute: Int) {

        dateToRequest.set(dateToRequest.get(Calendar.YEAR),dateToRequest.get(Calendar.MONTH),dateToRequest.get(Calendar.DAY_OF_MONTH),selectedHour,selectedMinute)
        tv_delivery_time_request_detail.text=Utility.fromCaldarToTimeSlot(dateToRequest)
    }
    private fun setDateRequestDateInFuture() {
        val calandar= Calendar.getInstance()
        calandar.add(Calendar.DAY_OF_MONTH,2)
        dateToRequest=calandar.clone() as Calendar
        linearLayout13.forEach{
            if (it is RadioButton) {
                it.text=calandar.get(Calendar.DAY_OF_MONTH).toString()
                it.tag=calandar.clone()
                it.setOnClickListener(clickedOnDay)
                calandar.add(Calendar.DAY_OF_MONTH,1)
            }
        }
    }
    private val clickedOnDay: View.OnClickListener =
        View.OnClickListener { view -> dateToRequest=view.tag as Calendar }
    private fun creatCODOrdert(): Order {
        val enddate:Calendar= dateToRequest.clone() as Calendar
        enddate.add(Calendar.MINUTE,dishInfo.TimeSlotInterval)
        return Order(
            BuyerId = Preference.getUserId(),
            ChefId = dishInfo.ChefId,
            SellerUserId = dishInfo.SellerUserId,
            DeliveryStartTime =Utility.fromCaldarToNetworkDate(calendar = dateToRequest),
            DeliveryEndTime =Utility.fromCaldarToNetworkDate(calendar = enddate),
            DeliveryCharge = dishInfo.DeliveryCharge,
            DishId = dishInfo.DishId,
            PackingCharge = dishInfo.PackingCharge,
            PackingOptions = dishInfo.PackingOptions,
            DeliveryOptions = dishInfo.DeliveryOptions,
            Discount = 0,
            ItemTotal = totalDishPrice,
            OrderQuantity = tv_quantity_detail.text.toString().toInt(),
            TotalDeliveryCharge = totalDeliveryCharges,/* cart.dishItem.quantity,*/
            TotalPackingCharge = totalPackingCharge,
            OrderTotal = totalPrice,
            PaymentMode = "COD",
            ServiceCharge = getServixceCharges(totalPrice),
            RequestStatus = "Acceptance Pending",
            RejectNote = ""
        )
    }
    private fun getServixceCharges(cartTotal: Int): Int {
        val serviceCharge: Int

        val slabRange = 4

        val result = (cartTotal - CONSTANT_HUNDRED).toFloat()/ CONSTANT_FIFTY.toFloat()
        val roundedUp = ceil(result.toDouble()) .toInt()
        serviceCharge = roundedUp * slabRange + 3

        // Return
        // Return
        return serviceCharge
    }

}
