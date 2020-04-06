package com.example.nextdoormvvm.buyer.ui.home.orderconfirmation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.Utility.DD_MM_YYYY_2
import com.example.nextdoormvvm.internal.Utility.HH_MM_A
import com.example.nextdoormvvm.internal.Utility.currentDate
import com.example.nextdoormvvm.buyer.ui.cart.CartViewModel
import com.example.nextdoormvvm.buyer.ui.cart.ShoppingCart
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.order_confirmation_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class OrderConfirmationFragment : Fragment() {

    private lateinit var cartModel: CartViewModel
    private var orderNo: Int?=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.order_confirmation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        orderNo =arguments?.getInt("orderId")
        (activity as AppCompatActivity).supportActionBar?.hide()
        tv_order_id_order_confirmation.text=orderNo.toString()
        activity?.findViewById<BottomNavigationView>(R.id.buyer_bottom_nav)?.visibility = View.GONE
        val today=currentDate(DD_MM_YYYY_2)
        tv_date_order_confirmation.text= today
        tv_time_order_confirmation.text= currentDate(HH_MM_A)
        tv_track_order_order_confirmation.setOnClickListener {

        }
        tv_return_home_order_confirmation.setOnClickListener {
            findNavController().popBackStack()
        }
        ShoppingCart.clearCart()

    }
    override fun onDestroyView() {
        super.onDestroyView()

        activity?.findViewById<BottomNavigationView>(R.id.buyer_bottom_nav)?.visibility = View.VISIBLE
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}
