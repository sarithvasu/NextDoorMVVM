package com.example.nextdoormvvm.buyer.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.response.Chef
import com.example.nextdoormvvm.buyer.network.response.Dish
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.internal.Utility
import kotlinx.android.synthetic.main.home_row.view.*


class HomeFeedAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private lateinit var m_HomeFeed: HomeFeedResponse
    private var m_itemCount: Int = -1
    private lateinit var mday: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeFeedViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.home_row, parent, false) )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // We can have meny view holder
        when(holder) {
            is HomeFeedViewHolder -> {
                if (mday == "Ongoing") {
                    holder.bindOngoingDish(m_HomeFeed, position)
                } else if (mday == "Tomorrow")  {
                    holder.bindTomorrowsDishes(m_HomeFeed, position)
                }
            }
        }
    }
    override fun getItemCount(): Int {
        //return m_HomeFeed.ongoingDishes.count()
        return m_itemCount
    }

    fun submitOngoingDishData(homeFeed: HomeFeedResponse, day: String){
        m_itemCount = homeFeed.ongoingDishes.count()
        m_HomeFeed = homeFeed
        mday = day
    }
    fun submitTomorrowsDishData(homeFeed: HomeFeedResponse, day: String){
        m_itemCount = homeFeed.tomorrowsDishes.count()
        m_HomeFeed = homeFeed
        mday = day
    }

    class HomeFeedViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindOngoingDish(homeFeed: HomeFeedResponse, position: Int){

            val chefInfo: Chef = homeFeed.chefs.filter { s -> homeFeed.ongoingDishes[position].ChefId == s.ChefId }[0]
            val dishInfo: Dish = homeFeed.ongoingDishes[position]
            itemView.tv_dish_name_home.text = dishInfo.DishName
            itemView.tv_dish_name_home.text = dishInfo.DishName
            itemView.tv_dish_price_home.text = " Rs. ${dishInfo.UnitPrice}"
            itemView.tv_serving_home.text = dishInfo.ServingsPerPlate.toString()
            itemView.tv_chef_name_with_flat_number_home.text = chefInfo.FullName + " | " + chefInfo.FlatNumber

            itemView.tv_dish_available_time_home.text =
                (Utility.standardDateToTimeSlot(dishInfo.DishAvailableStartTime) + " | " + Utility.standardDateToTimeSlot(
                    dishInfo.DishAvailableEndTime
                ))
            // Its a glide object Image setup
            itemView.img_dish_image_home.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.productDetailFragment))


            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(dishInfo.DishImageUrl)
                .into(itemView.img_dish_image_home)

            // Set ClickListener for buy Button
            itemView.btn_add_remove.configDishItem(dishInfo)
        }
        fun bindTomorrowsDishes(homeFeed: HomeFeedResponse, position: Int){

            val chefInfo: Chef = homeFeed.chefs.filter { s -> homeFeed.tomorrowsDishes[position].ChefId == s.ChefId }[0]
            val dishInfo: Dish = homeFeed.tomorrowsDishes[position]

            itemView.tv_dish_name_home.text = dishInfo.DishName
            itemView.tv_dish_name_home.text = dishInfo.DishName
            itemView.tv_dish_price_home.text = " Rs. ${dishInfo.UnitPrice}"
            itemView.tv_serving_home.text = dishInfo.ServingsPerPlate.toString()
            itemView.tv_chef_name_with_flat_number_home.text = chefInfo.FullName + " | " + chefInfo.FlatNumber

            itemView.tv_dish_available_time_home.text =
                (Utility.standardDateToTimeSlot(dishInfo.DishAvailableStartTime) + " | " + Utility.standardDateToTimeSlot(
                    dishInfo.DishAvailableEndTime
                ))
            // Its a glide object Image setup
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(dishInfo.DishImageUrl)
                .into(itemView.img_dish_image_home)


            // Set ClickListener for buy Button
            itemView.btn_add_remove.configDishItem(dishInfo)
        }


    }

}