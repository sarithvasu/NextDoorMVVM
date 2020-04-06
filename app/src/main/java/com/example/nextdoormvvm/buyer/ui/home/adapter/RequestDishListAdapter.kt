package com.example.nextdoormvvm.buyer.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.response.Chef
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.buyer.network.response.InactiveDish
import kotlinx.android.synthetic.main.add_remove_layout.view.*
import kotlinx.android.synthetic.main.request_list_row.view.*

class RequestDishListAdapter  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var m_HomeFeed: HomeFeedResponse
    private var m_itemCount: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeFeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.request_list_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // We can have meny view holder
        when (holder) {
            is HomeFeedViewHolder -> {

                holder.bindRequestDish(m_HomeFeed, position)

            }
        }
    }

    override fun getItemCount(): Int {
        //return m_HomeFeed.ongoingDishes.count()
        return m_itemCount
    }

    fun submitRequestDishData(homeFeed: HomeFeedResponse) {
        m_itemCount = homeFeed.inactiveDishes.count()
        m_HomeFeed = homeFeed

    }



    class HomeFeedViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindRequestDish(homeFeed: HomeFeedResponse, position: Int) {

            val chefInfo: Chef = homeFeed.chefs.filter { s -> homeFeed.ongoingDishes[position].ChefId == s.ChefId }[0]
            val dishInfo: InactiveDish = homeFeed.inactiveDishes[position]
            itemView.tv_dish_name_req.text = dishInfo.DishName
            itemView.tv_dish_name_req.text = dishInfo.DishName
            itemView.tv_dish_price_req.text = " Rs. ${dishInfo.UnitPrice}"
            itemView.tv_serving_req.text = dishInfo.ServingsPerPlate.toString()
            itemView.tv_chef_name_with_flat_number_req.text =
                chefInfo.FullName + " | " + chefInfo.FlatNumber


            // Its a glide object Image setup
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(dishInfo.DishImageUrl)
                .into(itemView.img_dish_image_req)

            // Set ClickListener for buy Button
            val bundle = bundleOf("dishInfo" to dishInfo)
            itemView.btn_req.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.makeRequestFragment,bundle))
        }




    }
}