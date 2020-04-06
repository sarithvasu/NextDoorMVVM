package com.example.nextdoormvvm.buyer.ui.home.timeslot

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.response.Dish
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.internal.Utility.NETWORK_STANDARD_TIME_2
import com.example.nextdoormvvm.internal.Utility.currentDate
import com.example.nextdoormvvm.internal.Utility.getDeliveryOptionFromDescription
import com.example.nextdoormvvm.internal.Utility.getPackingyOptionFromDescription
import com.example.nextdoormvvm.internal.listners.OnTimeSlotSelectListener
import com.example.nextdoormvvm.buyer.ui.cart.CartItem
import com.example.nextdoormvvm.buyer.ui.cart.DishItem
import com.example.nextdoormvvm.buyer.ui.cart.ShoppingCart
import kotlinx.android.synthetic.main.time_slot_fragment.*
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TimeSlotFragment : Fragment(), View.OnClickListener, OnTimeSlotSelectListener {

    companion object {
        fun newInstance() =
            TimeSlotFragment()
    }

    private lateinit var mDishItem: DishItem
    private val mDelimiter = "("
    private val mDelimiterHyphen = "-"

    private var timeSlotText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.time_slot_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dishInfo = arguments?.getParcelable<Dish>("dishInfo")

        rb_packing_disposable.setOnClickListener(packingOptionClicked)
        rb_packing_bring_own.setOnClickListener(packingOptionClicked)

        rb_home_delivery.setOnClickListener(deliveryOptionClicked)
        rb_self_pick.setOnClickListener(deliveryOptionClicked)

        when (dishInfo?.DeliveryOptions) {
            Utility.DeliveryOption.Both.option -> {
                rb_home_delivery.text =
                    "${Utility.DeliveryOption.Home_delivery.name.replace(
                        "_",
                        " "
                    )} ( Rs ${dishInfo.DeliveryCharge} Extra)"
                rb_self_pick.text = Utility.DeliveryOption.Self_pick.name.replace("_", " ")
                rb_home_delivery.isEnabled = true
                rb_self_pick.isEnabled = true
            }
            Utility.DeliveryOption.Home_delivery.option -> {
                rb_home_delivery.text =
                    "${Utility.DeliveryOption.Home_delivery.name.replace(
                        "_",
                        " "
                    )} ( Rs ${dishInfo.DeliveryCharge} Extra)"
                rb_home_delivery.isEnabled = true
            }
            Utility.DeliveryOption.Self_pick.option -> {
                rb_home_delivery.isEnabled = false
            }
        }

        when (dishInfo?.PackingOptions) {
            Utility.PackingOption.Both.option -> {
                rb_packing_disposable.text =
                    "${Utility.PackingOption.Parcel_in_disposable_box.name.replace(
                        "_",
                        " "
                    )} ( Rs ${dishInfo.PackingCharge} Extra)"
                rb_packing_bring_own.text =
                    Utility.PackingOption.Get_your_own_box.name.replace("_", " ")
                rb_packing_disposable.isEnabled = true
                rb_packing_bring_own.isEnabled = true
            }
            Utility.PackingOption.Parcel_in_disposable_box.option -> {
                rb_packing_disposable.text =
                    "${Utility.PackingOption.Parcel_in_disposable_box.option} ( Rs ${dishInfo.PackingCharge} Extra)"
                rb_packing_disposable.isEnabled = true
            }
            Utility.PackingOption.Get_your_own_box.option -> {
                rb_packing_disposable.isEnabled = false
            }
        }

        mDishItem = DishItem()
        mDishItem.dishId = dishInfo!!.DishId
        mDishItem.dishName=dishInfo.DishName
        mDishItem.quantity = 1
        mDishItem.sellerId = dishInfo.SellerUserId
        mDishItem.deliveryCharge=dishInfo.DeliveryCharge
        mDishItem.packingCharge=dishInfo.PackingCharge
        mDishItem.unitPrice = dishInfo.UnitPrice
        mDishItem.chefId = dishInfo.ChefId
        mDishItem.deliveryEndTime = dishInfo.DishAvailableEndTime
        mDishItem.deliveryStartTime = dishInfo.DishAvailableStartTime


        val timeSlots: TimeSlots = createTimeSlots(
            currentDate(NETWORK_STANDARD_TIME_2),
            dishInfo.DishAvailableEndTime,
            dishInfo.TimeSlotInterval
        )
        setTimeRangeLabel(timeSlots)
        rv_after_noon_time_slots.apply {
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
            adapter = TimeSlotAdapter(
                this@TimeSlotFragment,
                timeSlots.afterNoonTimeSlots,
                TimeSlotAdapter.AFTER_NOON_SLOTS
            )
        }

        ViewCompat.setNestedScrollingEnabled(nsc_morninf_time_slot, true)
        rv_evening_time_slots.apply {
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
            adapter = TimeSlotAdapter(
                this@TimeSlotFragment,
                timeSlots.eveningTimeSlots,
                TimeSlotAdapter.EVENING_SLOTS
            )
        }
        rv_morning_time_slots.apply {
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
            adapter = TimeSlotAdapter(
                this@TimeSlotFragment,
                timeSlots.morningTimeSlots,
                TimeSlotAdapter.MORNING_SLOTS
            )
        }
        ViewCompat.setNestedScrollingEnabled(nsc_morninf_time_slot, false)
        ViewCompat.setNestedScrollingEnabled(nsc_after_noon_time_slot, false)
        ViewCompat.setNestedScrollingEnabled(nsc_evening_time_slot, false)
        ViewCompat.setNestedScrollingEnabled(rv_after_noon_time_slots, false)
        ViewCompat.setNestedScrollingEnabled(rv_evening_time_slots, false)
        ViewCompat.setNestedScrollingEnabled(rv_morning_time_slots, false)
        btn_confirm_slots.setOnClickListener { start() }
    }

    private fun setTimeRangeLabel(timeSlots: TimeSlots) {

        if (timeSlots.morningTimeSlots.size == 0) textview6.visibility = GONE
        if (timeSlots.afterNoonTimeSlots.size == 0) time_slot_after_noon_label_txt.visibility = GONE
        if (timeSlots.eveningTimeSlots.size == 0) textView7.visibility = GONE


    }


    private fun start() {
        if (timeSlotText != "" && mDishItem.packingOptions != -1 && mDishItem.deliveryOptions != -1) {

            mDishItem.deliveryStartTime = getConvertedDate(
                currentDate(NETWORK_STANDARD_TIME_2),
                timeSlotText.split(mDelimiterHyphen)[0].trim()
            )
            mDishItem.deliveryEndTime = getConvertedDate(
                currentDate(NETWORK_STANDARD_TIME_2),
                timeSlotText.split(mDelimiterHyphen)[1].trim()
            )

            ShoppingCart.addToCart(CartItem(mDishItem))

            findNavController().popBackStack(R.id.timeSlotFragment, true)


            //no need to specify a activity when setting a result

        } else {
            when {
                timeSlotText.isEmpty() -> Utility.toastThis("Please select a delivery time")
                mDishItem.packingOptions == -1 -> Utility.toastThis("Please select a packing option")
                mDishItem.deliveryOptions == -1 -> Utility.toastThis("Please select a delivery option")
            }
        }
    }
    private fun getConvertedDate(deliveryStartTime: String?, trim: String): String {
        deliveryStartTime?.split("T")?.get(0)
        return "${deliveryStartTime?.split("T")?.get(0)}T${Utility.change24hoursTimeSlot(trim)}"
    }


    private val deliveryOptionClicked = View.OnClickListener { v ->
        if (v is RadioButton) {
            // Is the button now checked?
            val checked = v.isChecked


            var strDeliveryDescription = ""
            // Check which radio button was clicked
            when (v.getId()) {
                R.id.rb_home_delivery ->
                    if (checked) {
                        strDeliveryDescription = rb_home_delivery.text.toString()
                    }
                R.id.rb_self_pick ->
                    if (checked) {
                        strDeliveryDescription = rb_self_pick.text.toString()
                    }
            }

            val strDescription = strDeliveryDescription.split(mDelimiter)[0].trim()
            mDishItem.deliveryOptions =
                getDeliveryOptionFromDescription(strDescription.replace(" ", "_"))

        }
    }

    private val packingOptionClicked = View.OnClickListener { v ->
        if (v is RadioButton) {
            // Is the button now checked?
            val checked = v.isChecked

            var strPackingDescription = ""
            // Check which radio button was clicked
            when (v.getId()) {

                R.id.rb_packing_disposable ->
                    if (checked) {

                        strPackingDescription = rb_packing_disposable.text.toString()
                        // if(!isNotHomeDelivery)
                        // rb_home_delivery.isEnabled=true

                    }
                R.id.rb_packing_bring_own ->
                    if (checked) {
                        strPackingDescription = rb_packing_bring_own.text.toString()
                    }
            }
            //Toast.makeText(applicationContext,"${strPackingDescription}", Toast.LENGTH_SHORT).show()
            mDishItem.packingOptions = getPackingyOptionFromDescription(
                strPackingDescription.split(mDelimiter)[0].trim().replace(" ", "_")
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun createTimeSlots(
        startTimeNetwork: String,
        EndTimeNetwork: String,
        interval: Int
    ): TimeSlots {

        val sdf = SimpleDateFormat(Utility.HH_MM_A)
        val endTimeStr = Utility.standardDateToTimeSlot(EndTimeNetwork)
        val startTime = Utility.standardDateToTimeSlot(startTimeNetwork)
        val endTime: Date = sdf.parse(endTimeStr)
        var currentDate: Date = sdf.parse(startTime)
        val noon: Date = sdf.parse("12:00 PM")
        val evening: Date = sdf.parse("04:01 PM")

        val timeSlotsMorning: ArrayList<String> = arrayListOf()
        val timeSlotsAfterNoon: ArrayList<String> = arrayListOf()
        val timeSlotsEvening: ArrayList<String> = arrayListOf()
        val timeSlots = TimeSlots(timeSlotsMorning, timeSlotsAfterNoon, timeSlotsEvening)

        while (currentDate.before(endTime)) {
            val calendar = Calendar.getInstance()
            calendar.time = currentDate
            val strDate = sdf.format(currentDate)
            calendar.add(Calendar.MINUTE, interval)
            currentDate = calendar.time
            val endDate = sdf.format(currentDate)
            if (currentDate.after(noon) && currentDate.before(evening)) {
                timeSlotsAfterNoon.add(strDate.toUpperCase(Locale.getDefault()) + "-" + endDate.toUpperCase(
                    Locale.getDefault()))
            } else if (currentDate.after(evening)) {
                timeSlotsEvening.add(strDate.toUpperCase(Locale.getDefault()) + "-" + endDate.toUpperCase(
                    Locale.getDefault()))
            } else {
                timeSlotsMorning.add(strDate.toUpperCase(Locale.getDefault()) + "-" + endDate.toUpperCase(
                    Locale.getDefault()))
            }
        }
        return timeSlots
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rb_packing_disposable -> {

            }
        }
    }

    override fun updateOther(recyclerViewOfType: Int, timeSlotText: String) {
        this.timeSlotText = timeSlotText
        when (recyclerViewOfType) {
            TimeSlotAdapter.MORNING_SLOTS -> {
                val adapter: TimeSlotAdapter = rv_evening_time_slots.adapter as TimeSlotAdapter
                adapter.rowIndex = -1
                adapter.notifyDataSetChanged()
                val adapter1: TimeSlotAdapter = rv_after_noon_time_slots.adapter as TimeSlotAdapter
                adapter1.rowIndex = -1
                adapter1.notifyDataSetChanged()
            }
            TimeSlotAdapter.EVENING_SLOTS -> {
                val adapter: TimeSlotAdapter = rv_morning_time_slots.adapter as TimeSlotAdapter
                adapter.rowIndex = -1
                adapter.notifyDataSetChanged()
                val adapter1: TimeSlotAdapter = rv_after_noon_time_slots.adapter as TimeSlotAdapter
                adapter1.rowIndex = -1
                adapter1.notifyDataSetChanged()
            }
            TimeSlotAdapter.AFTER_NOON_SLOTS -> {
                val adapter: TimeSlotAdapter = rv_evening_time_slots.adapter as TimeSlotAdapter
                adapter.rowIndex = -1
                adapter.notifyDataSetChanged()
                val adapter1: TimeSlotAdapter = rv_morning_time_slots.adapter as TimeSlotAdapter
                adapter1.rowIndex = -1
                adapter1.notifyDataSetChanged()
            }
        }
    }

}


