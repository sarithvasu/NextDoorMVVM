@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.nextdoormvvm.internal

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.navigation.Navigation
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.ui.cart.CartItem
import com.example.nextdoormvvm.buyer.ui.cart.ShoppingCart
import kotlinx.android.synthetic.main.checkout_btn_layout.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    const val HH_MM_A = "hh:mm a"
    private const val HH_MM_SS = "HH:mm:ss"
    const val CURRENT_DATE = "CURRENT_DATE"
    const val USER_TYPE_ID = "USER_TYPE_ID"


    const val DD_MM_YYYY_2 = "dd/MM/YYYY"

    const val NETWORK_STANDARD_TIME_2 = "yyyy-MM-dd'T'HH:mm:ss"
    private const val SELLER_DATE_TIME_FORMATE = "yyyy MMMM dd, hh:mm a"

    const val WHATS_APP_PACKAGE_NAME = "com.whatsapp"
    const val SHARE_BY_CHOOSER = 10
    /*const val SHARE_BY_CHOOSER_NEW = 1001*/


    enum class UserType(val value: Int) {
        BUYER(1),
        SELLER(2)
    }
    enum class SpecilizeOption(val value: Int) {
        VEG(2),
        NON_VEG(4),
        BOTH(6)
    }

    fun currentDate(dateFormate: String): String {
        val sdf1 = SimpleDateFormat(dateFormate, Locale.US)
        return sdf1.format(Date())
    }


    fun getDeliveryOptionFromDescription(desc: String): Int {
        return DeliveryOption.valueOf(desc).option
    }

    fun getPackingyOptionFromDescription(desc: String): Int {
        return PackingOption.valueOf(desc).option
    }

    fun standardDateToTimeSlot(date: String): String {
        return try {
            val sdf1 = SimpleDateFormat(NETWORK_STANDARD_TIME_2, Locale.US)
            val sdf2 = SimpleDateFormat(HH_MM_A, Locale.US)
            sdf2.format(sdf1.parse(date))
        } catch (e: ParseException) {
            e.localizedMessage
        }
    }
    fun convertDateFormate(dateFormateFrom: String,dateFormateTo: String, date: String): String {
        return try {
            val sdf1 = SimpleDateFormat(dateFormateFrom, Locale.US)
            val sdf2 = SimpleDateFormat(dateFormateTo, Locale.US)
            sdf2.format(sdf1.parse(date))
        } catch (e: ParseException) {
            e.localizedMessage
        }
    }
    fun fromNetworkToToSellerTimelot(date: String): String {
        return try {
            val sdf1 = SimpleDateFormat(NETWORK_STANDARD_TIME_2, Locale.US)
            val sdf2 = SimpleDateFormat(SELLER_DATE_TIME_FORMATE, Locale.US)
            sdf2.format(sdf1.parse(date))
        } catch (e: ParseException) {
            e.localizedMessage
        }
    }
    fun fromCaldarToTimeSlot(calendar: Calendar?): String {
        return try {
            val date = calendar?.time
            val sdf1 = SimpleDateFormat(HH_MM_A, Locale.US)
            sdf1.format(date)
        } catch (e: ParseException) {
            e.localizedMessage
        }
    }
    fun fromCaldarToNetworkDate(calendar: Calendar?): String {
        return try {
            val date = calendar?.time
            val sdf1 = SimpleDateFormat(NETWORK_STANDARD_TIME_2, Locale.US)
            sdf1.format(date)
        } catch (e: ParseException) {
            e.localizedMessage
        }
    }

    fun change24hoursTimeSlot(date: String): String {
        return try {
            val sdf1 = SimpleDateFormat(HH_MM_A, Locale.US)
            val sdf2 = SimpleDateFormat(HH_MM_SS, Locale.US)
            sdf2.format(sdf1.parse(date))
        } catch (e: ParseException) {
            e.localizedMessage
        }
    }

    fun isOnline(): Boolean {
        var result = false
        val cm = NextDoorApplication.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    fun toastThis(msg: String?) {
        Toast.makeText(NextDoorApplication.context, msg, Toast.LENGTH_SHORT).show()
    }

    private val arrSliderImages = arrayOf(
        R.drawable.kadai_panner,
        R.drawable.egg_biryani,
        R.drawable.roti_new,
        R.drawable.tandoori_new
    )

    enum class DeliveryOption(val option: Int) {
        Home_delivery(4),
        Self_pick(2),
        Both(6)
    }

    enum class PackingOption(val option: Int) {
        Parcel_in_disposable_box(4),
        Get_your_own_box(2),
        Both(6)
    }

    fun getImageSliderCount(): Int {
        return arrSliderImages.count()
    }

    fun changeColorOfChid(layout: LinearLayout, textView: View) {
        layout.forEach {
            if (it is TextView) {
                it.setTextColor(
                    ContextCompat.getColor(
                        layout.context,
                        R.color.Black
                    )
                )
                it.setBackgroundColor(
                    ContextCompat.getColor(
                        layout.context,
                        R.color.White
                    )
                )
                it.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)

            }
        }
        (textView as TextView).setTextColor(
            ContextCompat.getColor(
                layout.context,
                R.color.SkyBlue
            )
        )
        textView.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            null,
            ContextCompat.getDrawable(layout.context, R.drawable.bg_line)
        )
        textView.compoundDrawablePadding = 20
    }

    @SuppressLint("SetTextI18n")
    fun manageCheckoutButton(cartItems: ArrayList<CartItem>, view: View) {

        if (cartItems.size > 0) {

            view.visible()
            view.tv_item_count.text =
                ShoppingCart.getCartDetail().cartSummery.QuantityCount.toString() + " items"
            view.tv_total_amount.text =
                "Rs. " + ShoppingCart.getCartDetail().cartSummery.CartTotal.toString()


        } else {

            view.gone()


        }
        view.tv_proceed_btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.checkOutFragment))
    }

    private fun View.visible() {
        this.visibility = VISIBLE
    }

    private fun View.gone() {
        this.visibility = GONE
    }

    fun createCustomDialog(context: Context, msg: String, isOneBuuton:Boolean) {

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        (dialog.findViewById(R.id.tv_msg) as TextView).text = msg
        if(isOneBuuton) {
            (dialog.findViewById(R.id.btn_cancel) as Button).visibility = GONE
            (dialog.findViewById<View>(R.id.view27)).visibility= GONE
//                (dialog.findViewById<View>(R.id.view28)).visibility= GONE

        }
        (dialog.findViewById(R.id.btn_yes) as Button).setOnClickListener{ dialog.dismiss() }
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}