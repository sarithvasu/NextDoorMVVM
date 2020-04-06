package com.example.nextdoormvvm.buyer.ui.home.adapter


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.response.Chef
import com.example.nextdoormvvm.buyer.network.response.Dish
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.internal.Utility
import kotlinx.android.synthetic.main.home_row.view.*
import kotlinx.android.synthetic.main.home_row_secondary.view.*
import kotlin.random.Random


class HomeAdapter(val context: Context, private val homeFeed: HomeFeedResponse/*, var viewTypeHome: Int*/) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
/*    private lateinit var listener: OnItemCountListener
    private lateinit var dishSelected: OnActiveDishSelected
    private lateinit var m_listOfCartItem: ArrayList<CartItem>*/


/*
    companion object {
       const val REQUEST_CODE: Int = 100
    }*/


    override fun getItemCount(): Int {
        /*if (viewTypeHome == HomeActivity.VIEWTYPE_CHEF)
            return homeFeed.chefs.count()*/

        return homeFeed.ongoingDishes.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //this.listener = context as HomeActivity
        //this.dishSelected= context as HomeActivity
        /*  if (viewTypeHome == HomeActivity.VIEWTYPE_CHEF) {
              val layoutInflater = LayoutInflater.from(parent.context)
           //   m_listOfCartItem = ShoppingCart.getCartItems()



              val cellForRow = layoutInflater.inflate(R.layout.home_row_secondary, parent, false)
              return HomeFeedChefViewHolder(cellForRow, homeFeed)
          } else {*/
        val layoutInflater = LayoutInflater.from(parent.context)
        //m_listOfCartItem = ShoppingCart.getCartItems()



        val cellForRow = layoutInflater.inflate(R.layout.home_row, parent, false)
        return HomeFeedViewHolder(cellForRow/*, homeFeed*/)
        // }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HomeFeedViewHolder) {
            setControlHomeFeed(homeFeed, position, holder)
        } else if (holder is HomeFeedChefViewHolder) {

            val chefinfo: Chef = homeFeed.chefs[position]


            //Bind Chef Image
            /* val d = ContextCompat.getDrawable(context, R.drawable.profile_p_holder)
             val currentStrokeColor = Color.argb(255, Random.nextInt(200), Random.nextInt(200), Random.nextInt(200))
             val drawableNew1: Drawable = setTint(d, currentStrokeColor)*/
            /*  if (chefinfo.ProfileImageUrl.isEmpty()) {
               //   holder.itemView.img_chef_profile_home_row_sec.setImageResource(R.drawable.profile_p_holder)
                //  holder.itemView.img_chef_profile_home_row_sec.setColorFilter(Color.argb(255, Random.nextInt(200), Random.nextInt(200), Random.nextInt(200)))

              }
              else
              {
                  *//*PicassoClient.init(holder.itemView.context, DropboxClientFactory.init(Utility.DB_ACCESS_TOKEN)!!);
                PicassoClient.picasso?.load(ImagePiccassoRequestHadler.buildPicassoUri(Utility.SLASH +chefinfo.ProfileImageUrl))!!
                    .placeholder(R.drawable.dish_3)
                    .error(R.drawable.dish_3)
                    .into(holder.itemView.img_chef_profile_home_row_sec)*//*
            }*/

            holder.itemView.tv_chef_name_home_row_sec.text = chefinfo.FullName
            holder.itemView.tv_chef_address_with_flat_no_home_row_sec.text = chefinfo.FlatNumber
            holder.itemView.tv_about_chef_home_row_sec.text = chefinfo.AboutChef
            holder.itemView.tv_dish_type_veg_home_row_sec.text =  " Veg " + chefinfo.VegCount
            holder.itemView.tv_dish_type_nonveg_home_row_sec.text = " Non-Veg " + chefinfo.NonVegCount
            holder.itemView.tv_active_dishes_home_row_sec.text = " Active Dishes "



            val activeDishes: List<Dish> = homeFeed.ongoingDishes.filter { d-> d.ChefId == chefinfo.ChefId}

            // Set ClickListener for active signatureDishes
            holder.itemView.tv_active_dishes_home_row_sec.setOnClickListener {
                homeFeed.ongoingDishes.filter { d-> d.ChefId == chefinfo.ChefId}
                //dishSelected.updateDishesByChef(activeDishes)
            }


            // Set ClickListener for non veg signatureDishes
            holder.itemView.tv_dish_type_nonveg_home_row_sec.setOnClickListener {
                activeDishes.filter { d -> d.DishType == "Non_Veg" }
                //dishSelected.updateDishesByChef(vegDishes)

            }

            // Set ClickListener for veg signatureDishes
            holder.itemView.tv_dish_type_veg_home_row_sec.setOnClickListener {
                activeDishes.filter { d -> d.DishType == "Veg" }
                //dishSelected.updateDishesByChef(vegDishes)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setControlHomeFeed(homeFeed: HomeFeedResponse, position: Int, holder: RecyclerView.ViewHolder) {
        val chefinfo: Chef =
            homeFeed.chefs.filter { s -> homeFeed.ongoingDishes[position].ChefId == s.ChefId }[0]
        val dishInfo: Dish = homeFeed.ongoingDishes[position]

        if (dishInfo.DishImageUrl.isEmpty()) {
            holder.itemView.img_dish_image_home.setImageResource(R.drawable.dish_3)
            holder.itemView.img_dish_image_home.setColorFilter(Color.argb(255, Random.nextInt(200), Random.nextInt(200), Random.nextInt(200)))

        }
        /*  else {

            *//*  PicassoClient.init(holder.itemView.context, DropboxClientFactory.init(Utility.DB_ACCESS_TOKEN)!!);
            PicassoClient.picasso?.load(ImagePiccassoRequestHadler.buildPicassoUri(Utility.SLASH +dishInfo.DishImageUrl))!!
                .placeholder(R.drawable.dish_3)
                .error(R.drawable.dish_3)
                .into(holder.itemView.img_dish_image_home)*//*
        }*/
        // set Dish Symbol
        // val dishSymbol = Utility.setDishSymbol(dishInfo.DishType, holder.itemView.img_dish_symbol_home.context)
        // holder.itemView.img_dish_symbol_home.setImageDrawable(dishSymbol)

        holder.itemView.tv_dish_name_home.text = dishInfo.DishName
        holder.itemView.tv_dish_price_home.text = " Rs. ${dishInfo.UnitPrice}"
        holder.itemView.tv_serving_home.text = dishInfo.ServingsPerPlate.toString()
        holder.itemView.tv_chef_name_with_flat_number_home.text =
            chefinfo.FullName + " | " + chefinfo.FlatNumber
        holder.itemView.tv_dish_available_time_home.text =
            (Utility.standardDateToTimeSlot(dishInfo.DishAvailableStartTime) + " | " + Utility.standardDateToTimeSlot(
                dishInfo.DishAvailableEndTime
            ))
        //Bind Chef Image
        // val d = ContextCompat.getDrawable(context, R.drawable.profile_p_holder)
        //val drawableNew1: Drawable = setTint(d, currentStrokeColor1)
        /*if (chefinfo.ProfileImageUrl.isEmpty()) {
           *//* holder.itemView.img_chef_profile_home.setImageResource(R.drawable.profile_p_holder)
            holder.itemView.img_chef_profile_home.setColorFilter(Color.argb(255, Random.nextInt(200), Random.nextInt(200), Random.nextInt(200)))*//*

        }
        else {
           *//* Picasso.with(holder.itemView.context).load(chefinfo.ProfileImageUrl)
                .placeholder(R.drawable.profile_p_holder)
                .fit()
                .into(
                    holder.itemView.img_chef_profile_home
                )
            PicassoClient.init(holder.itemView.context, DropboxClientFactory.init(Utility.DB_ACCESS_TOKEN)!!);
            PicassoClient.picasso?.load(ImagePiccassoRequestHadler.buildPicassoUri(Utility.SLASH +chefinfo.ProfileImageUrl))!!
                .placeholder(R.drawable.profile_p_holder)
                .error(R.drawable.profile_p_holder)
                .into(holder.itemView.img_chef_profile_home)*//*
        }*/
        holder.itemView.tv_num_of_dish_reviews_home.text = dishInfo.DishAverageRating.toString()
        holder.itemView.tv_dish_sold_home.text = (dishInfo.DishSold).toString()
        holder.itemView.tv_dish_available_home.text = (dishInfo.AvailableQuantity.toString())
        // Seting the tag for dish Id for detail screen
        holder.itemView.tag = dishInfo.DishId


        // Set ClickListener for dish Image
        holder.itemView.img_dish_image_home.setOnClickListener {

            // it.findNavController().navigate(R.id.action_homeFragment_to_dishDetailsFragment)
            val bundle = bundleOf("Account" to dishInfo)
            Navigation.createNavigateOnClickListener(R.id.productDetailFragment,bundle)
            //Navigation.findNavController(context as Activity,R.id.buyer_home_content).navigate(R.id.action_homeFragment_to_dishDetailsFragment,bundle)
            /*  val intent = Intent(holder.itemView.img_dish_image_home.context, DishDetailActivity::class.java)
              intent.putExtra(Utility.DISH_ID_KEY, holder.itemView.getTag() as Int)
              intent.putExtra(Utility.SELLER_USER_ID_KEY, chefinfo.SellerUserId)
              holder.itemView.img_dish_image_home.context.startActivity(intent)*/
        }


        // Set ClickListener for chef profile Image
        holder.itemView.img_chef_profile_home.setOnClickListener {
            /*  val intent = Intent(holder.itemView.img_chef_profile_home.context, ChefProfileActivity::class.java)
              intent.putExtra(Utility.CHEF_ID_KEY, chefinfo.ChefId as Int)
              intent.putExtra(Utility.SELLER_USER_ID_KEY, chefinfo.SellerUserId as Int)
              holder.itemView.img_chef_profile_home.context.startActivity(intent)*/
        }

        // Disable buy button and enable (+) and (-) button If the item is already added to the cart



        // Set ClickListener for buy Button
        // holder.itemView.btn_add_remove.configDishItem(dishInfo,listener)
    }







}


class HomeFeedViewHolder(val view: View/*, val homeFeed: HomeFeedResponse?*/) : RecyclerView.ViewHolder(view)



class HomeFeedChefViewHolder(val view: View /*, val homeFeed: HomeFeedResponse?*/) : RecyclerView.ViewHolder(view)





