package com.example.nextdoormvvm.buyer.ui.home.listofchef

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.response.Chef
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import kotlinx.android.synthetic.main.home_row.view.*
import kotlinx.android.synthetic.main.home_row_secondary.view.*


class ChefListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var m_HomeFeed: HomeFeedResponse

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChefViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_row_secondary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // We can have meny view holder
        when (holder) {
            is ChefViewHolder -> {
                holder.bindChef(m_HomeFeed, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return m_HomeFeed.chefs.count()
    }

    fun submitChefListData(homeFeed: HomeFeedResponse) {
        m_HomeFeed = homeFeed
    }


    class ChefViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindChef(homeFeed: HomeFeedResponse, position: Int) {

            val chefInfo: Chef = homeFeed.chefs[0]

            itemView.tv_chef_name_home_row_sec.text = chefInfo.FullName
            itemView.tv_chef_address_with_flat_no_home_row_sec.text = chefInfo.FlatNumber
            itemView.tv_about_chef_home_row_sec.text = chefInfo.AboutChef
            itemView.tv_dish_type_veg_home_row_sec.text = " Veg " + chefInfo.VegCount
            itemView.tv_dish_type_nonveg_home_row_sec.text = " Non-Veg " + chefInfo.NonVegCount
            itemView.tv_active_dishes_home_row_sec.text = " Active Dishes "


            // Its a glide object Image setup
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

//            Glide.with(itemView.context)
//                .applyDefaultRequestOptions(requestOptions)
//                .load(chefInfo.ProfileImageUrl)
//                .into(itemView.img_dish_image_home)
        }
    }
}