package com.example.nextdoormvvm.buyer.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.buyer.ui.cart.ShoppingCart
import com.example.nextdoormvvm.buyer.ui.cart.UpdateType
import kotlinx.android.synthetic.main.checkout_row.view.*


class CheckoutAdapter() : RecyclerView.Adapter<OrderDetailViewHolder>() {


    private var mTotalPackingCharges:Int = 0 // Packing charges is applicable per dish Item


    override fun getItemCount(): Int {
        return ShoppingCart.getCartDetail().cartItems.size
      }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): OrderDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dishRowView = layoutInflater.inflate(R.layout.checkout_row, parent, false)
      //  this.listener= context as CheckoutActivity
        return OrderDetailViewHolder(dishRowView)
    }
    override fun onBindViewHolder(holder: OrderDetailViewHolder, position: Int) {
        val cartItem = ShoppingCart.getCartDetail().cartItems[position]
        //val dishInfo: HomeFeed.Dish? = mDishes.find { d -> d.DishId == cartItem.dishItem.dishId }


            // set Dish Symbol
            /*val dishSymbol = Utility.setDishSymbol(dishInfo!!.DishType, holder.view.img_dish_symbol_rv.context)
            holder.view.img_dish_symbol_rv.setImageDrawable(dishSymbol)*/

            holder.view.tv_dish_name_rv.text = cartItem.dishItem.dishName
            holder.view.tv_dish_price_rv.text = "\u20B9 ${cartItem.dishItem.unitPrice}"
            holder.view.tv_dish_quantity_rv.text = cartItem.dishItem.quantity.toString()
            holder.view.tv_dish_delivery_time_rv.text =
                Utility.standardDateToTimeSlot(cartItem.dishItem.deliveryStartTime) + " | " +  Utility.standardDateToTimeSlot(cartItem.dishItem.deliveryEndTime)
            holder.view.tv_dish_packing_type_rv.text = cartItem.dishItem.packingCharge.toString() //cartItem.dishItem.packingTypeId.toString()

            //holder.view.tv_dish_delivery_type_rv.text =  Manager.Companion.Preference().getDeliveryDescriptionById(cartItem.dishItem.deliveryTypeId)
            holder.view.tv_dish_delivery_type_rv.text =  cartItem.dishItem.deliveryCharge.toString()


            val itemTotal: Int = (cartItem.dishItem.unitPrice * cartItem.dishItem.quantity)
            holder.view.tv_total_unit_price_rv.text = "₹ ${itemTotal.toString()}"

            val packingCharges: Int = cartItem.dishItem.packingCharge
            mTotalPackingCharges += packingCharges
            holder.view.tv_packing_charges_rv.text = (packingCharges).toString()

            holder.view.tv_delivery_charges_rv.text =cartItem.dishItem.deliveryCharge.toString()
            if (holder.view.tv_dish_quantity_rv.text == 0.toString()) {
                holder.view.tv_delivery_charges_rv.text = "0"
            }
            holder.view.tv_total_dish_price_rv.text ="₹ ${itemTotal+packingCharges+ cartItem.dishItem.deliveryCharge}"
                    // Reset  Variables to Zero
            //itemTotal = 0
            mTotalPackingCharges = 0

            // Set ClickListener for + Button
            holder.view.tv_plus_chechout_rv.setOnClickListener {
                var count = holder.view.tv_dish_quantity_rv.text.toString().toInt()

                if (count < 99) {
                    count++
                    ShoppingCart.updateCartQuantity(cartItem.dishItem.dishId, UpdateType.ADD)
                    holder.view.tv_dish_quantity_rv.text = count.toString()
                    notifyItemChanged(position)
                   // listener.updateCount()
                } else {
                    // Soumen Need to write tost
                }
            }


            holder.view.tv_minus_checkout_rv.setOnClickListener {
                var count = holder.view.tv_dish_quantity_rv.text.toString().toInt()

                if (count > 1) {
                    count--
                    ShoppingCart.updateCartQuantity(cartItem.dishItem.dishId, UpdateType.REMOVE)
                    notifyItemChanged(position)
                   // listener.updateCount()
                    holder.view.tv_dish_quantity_rv.text = count.toString()
                } else {
                  /*  Utility.U(cartItem.dishItem.dishId)
                    holder.view.tv_dish_quantity_rv.text = "0"
                    notifyDataSetChanged()*/
                   // listener.updateCount()
                }

        }
    }





/*
    private fun getDeliveryCharges(dishInfo: HomeFeed.Dish, dishItem: DishItem): Int {
        var deliveryAmount = 0

        // deliveryTypeId = 1= => Home delivery
        if (dishItem.deliveryOptionId == DeliveryOption.Home_delivery.option) {
            deliveryAmount = dishInfo.DeliveryCharge
        }

        return deliveryAmount
    }
    private fun getPackingCharges(dishInfo: HomeFeed.Dish, dishItem: DishItem): Int {
        var packingAmount = 0

        // packingTypeId = 2 => Parcel in disposable box
        if (dishItem.packingOptionId == PackingOption.Parcel_in_disposable_box.option) {
            packingAmount += (dishInfo.PackingCharge * dishItem.quantity)
        }
        return packingAmount
    }*/
}



class OrderDetailViewHolder(var view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
}

