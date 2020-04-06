package com.example.nextdoormvvm.common.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.common.network.response.OrderSummary
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.internal.Utility.DD_MM_YYYY_2
import com.example.nextdoormvvm.internal.Utility.NETWORK_STANDARD_TIME_2
import com.example.nextdoormvvm.internal.Utility.convertDateFormate
import com.example.nextdoormvvm.internal.Utility.currentDate
import kotlinx.android.synthetic.main.order_history_row.view.*


class OrderHistoryAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mOrderHistoryFeed: ArrayList<OrderSummary>
    private var mItemcount: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, itemViewType: Int): RecyclerView.ViewHolder {
        return HomeFeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_history_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // We can have meny itemView holder
        when (holder) {
            is HomeFeedViewHolder -> {

                holder.bindRequestDish(mOrderHistoryFeed, position)

            }
        }
    }

    override fun getItemCount(): Int {
        //return m_HomeFeed.ongoingDishes.count()
        return mItemcount
    }

    fun submitRequestDishData(orderHistoryFeed: ArrayList<OrderSummary>) {
        this.mItemcount = orderHistoryFeed.count()
        mOrderHistoryFeed = orderHistoryFeed

    }



    class HomeFeedViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindRequestDish(orderHistoryFeed: ArrayList<OrderSummary>, position: Int) {
            val orderSummery = orderHistoryFeed[position]
            val dateDelivered = Utility.fromNetworkToToSellerTimelot(orderSummery.OrderDate)
            if (dateDelivered.startsWith("0")) itemView.tv_order_date_and_time_history_row.text =
                "" else itemView.tv_order_date_and_time_history_row.text = dateDelivered
            // itemView.tv_order_date_and_time_history_row.text = Utility.standardDate( Utility.NETWORK_STANDARD_TIME,myOrder.OrderDate)
            // itemView.tv_dish_name_history_row.text= myOrder.dish_name
            // itemView.tv_unit_price_history_row.text= myOrder.unit_price.toString()

            // itemView.textView31.text= myOrder..toString()+" Item/s"
            itemView.tv_dish_total_price_history_row.text = "Rs ${orderSummery.OrderTotal}"
            if (orderSummery.ChefId != 0) {
                itemView.tv_chef_name_history_row.text = orderSummery.ChefName
                itemView.textView153.text = "Chef:"
                itemView.tv_chef_flat_number_history_row.text = orderSummery.ChefFlatNumber
                if (currentDate(DD_MM_YYYY_2) != (convertDateFormate(
                        NETWORK_STANDARD_TIME_2,
                        DD_MM_YYYY_2,
                        orderSummery.OrderDate
                    ))
                ) {
                    itemView.textView46.visibility = VISIBLE
                    itemView.tv_write_review_history_row.visibility = VISIBLE
                    itemView.ratingBar_history_row.visibility = VISIBLE
                }
            } else {
                itemView.tv_chef_name_history_row.text = orderSummery.BuyerName
                itemView.textView153.text = "User:"
                itemView.tv_chef_flat_number_history_row.text = orderSummery.BuyerFlatNumber
            }
            // itemView.textView156.text=myOrder.
            itemView.tv_dish_name_history_row.text=orderSummery.DishName
            itemView.textView31.text = "${orderSummery.OrderQuantity} items"
            itemView.textView156.text = "Rs ${orderSummery.DeliveryCharge + orderSummery.PackingCharge}"
            itemView.tv_unit_price_history_row.text = "Rs ${orderSummery.ItemTotal}"

            itemView.tv_order_status_history_row.text = orderSummery.OrderStatus
            //itemView.ratingBar_history_row.rating= myOrder.

            itemView.tv_payment_mode_history_row.text = orderSummery.PaymentMode

            itemView.tv_order_id_history_row.text = orderSummery.GroupId.toString()

        }




    }
}