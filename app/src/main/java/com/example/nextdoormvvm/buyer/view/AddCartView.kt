package com.example.nextdoormvvm.buyer.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.response.Dish
import com.example.nextdoormvvm.buyer.ui.cart.CartItem
import com.example.nextdoormvvm.buyer.ui.cart.CartViewModel
import com.example.nextdoormvvm.buyer.ui.cart.ShoppingCart
import com.example.nextdoormvvm.buyer.ui.cart.UpdateType
import kotlinx.android.synthetic.main.add_remove_layout.view.*


class AddCartView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.add_remove_layout, this, true)
        orientation = VERTICAL
    }

    fun configDishItem(dishInfo: Dish){

            ViewModelProvider(context as FragmentActivity).get(CartViewModel::class.java).apply {
                receiveCartDetail().observe(context as FragmentActivity, Observer {
                    val cartItem: CartItem? =
                        it.cartItems.find { s -> s.dishItem.dishId == dishInfo.DishId }
                    if (cartItem != null && cartItem.dishItem.quantity > 0) {
                        btn_lay_detail.visibility = View.VISIBLE
                        btn_buy_detail.visibility = View.GONE
                        tv_quantity_detail.text = cartItem.dishItem.quantity.toString()
                    } else {
                        btn_lay_detail.visibility = View.GONE
                        btn_buy_detail.visibility = View.VISIBLE
                    }
                })
            }

        val bundle = bundleOf("dishInfo" to dishInfo)
        btn_buy_detail.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.timeSlotFragment,bundle))

        tv_plus_detail.setOnClickListener {
            var count = tv_quantity_detail.text.toString().toInt()
            if (count < 99) {
                ShoppingCart.updateCartQuantity(dishInfo.DishId, UpdateType.ADD)
                count++
                tv_quantity_detail.text = count.toString()
            }
        }

        tv_minus_detail.setOnClickListener {
            var count = tv_quantity_detail.text.toString().toInt()
            if (count > 1) {
                count--
                ShoppingCart.updateCartQuantity(dishInfo.DishId, UpdateType.REMOVE)
                tv_quantity_detail.text = count.toString()
            } else {
                ShoppingCart.removeFromCart(dishInfo.DishId)
                btn_lay_detail.visibility = View.GONE
                btn_buy_detail.visibility = View.VISIBLE
            }
        }
    }

}