package com.example.nextdoormvvm.buyer.ui.home.ongoing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.buyer.network.response.Dish
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.buyer.ui.home.adapter.HomeFeedAdapter
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.buyer.ui.cart.CartViewModel
import com.example.nextdoormvvm.buyer.ui.cart.ShoppingCart
import kotlinx.android.synthetic.main.filter_layout.*
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ConnectException

class OngoingDishesFragment : ScopedFragment(),View.OnClickListener {

    private lateinit var mViewModel: OngoingDishesViewModel
    private lateinit var mHomeFeedInstance: HomeFeedResponse
    private lateinit var mHomeFeedAdapter: HomeFeedAdapter
    private lateinit var cartModel: CartViewModel

    private lateinit var mNavController: NavController

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.ongoing_dishes_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This is required for Navigating to chefListFragment
        mNavController = findNavController()
        val queryParameterMap = mapOf("ApartmentId" to "1")
        mViewModel = BuyerModelGenerator.getModel(this,BuyerViewModelTypeEnum.OngoingDishesViewModel, queryParameterMap ) as OngoingDishesViewModel
        getHomeFeedDataApi()
        ShoppingCart.setToLiveData(activity!!)
        cartModel= ViewModelProvider(activity!! ).get(CartViewModel::class.java)
        cartModel.receiveCartDetail().observe(activity!!, Observer {
            Utility.manageCheckoutButton(ShoppingCart.getCartDetail().cartItems,view.findViewById<LinearLayout>(R.id.proced_lay))
        })
        /*tv_proceed_btn.setOnClickListener {
            findNavController().navigate(R.id.action_ongoingDishesFragment_to_checkOutFragment)
        }
*/
        Utility.changeColorOfChid(layout_filter, tv_filter_all)



    }
    override fun onClick(v: View?) {
        lateinit var dishes: List<Dish>
        when(v){

            tv_filter_chef -> {
                val bundle = bundleOf("homeFeed" to mHomeFeedInstance)
                mNavController.navigate(  R.id.action_ongoingDishesFragment_to_chefListFragment, bundle)
                return
            }

            tv_filter_all ->{dishes = mHomeFeedInstance.ongoingDishes}
            tv_filter_veg ->{dishes = mHomeFeedInstance.ongoingDishes.filter{ d -> d.DishType == "Veg" } }
            tv_filter_non_veg ->{  dishes = mHomeFeedInstance.ongoingDishes.filter{ d -> d.DishType == "Non-Veg" } }
            tv_filter_breakfast ->{  dishes = mHomeFeedInstance.ongoingDishes.filter{ d -> d.MealType == "Breakfast" } }
            tv_filter_lunch -> { dishes = mHomeFeedInstance.ongoingDishes.filter{ d -> d.MealType == "Lunch" }}
            tv_filter_dinner ->{dishes = mHomeFeedInstance.ongoingDishes.filter{ d -> d.MealType == "Dinner" } }
            tv_filter_snacks ->{dishes = mHomeFeedInstance.ongoingDishes.filter{ d -> d.MealType == "Snacks" }}
        }
        reLoadOngoingDishRecyclerView(dishes)
        Utility.changeColorOfChid(layout_filter, v!!)
    }


    // Its a coroutine  function
    private fun getHomeFeedDataApi() = launch(Dispatchers.Main) {
        try {
            val currentWeather = mViewModel.homeFeed.await()
            currentWeather.observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer

                ongoing_dish_loading.visibility = View.GONE
                mHomeFeedInstance = it
                initBasicSetup()
            })
        }
            catch (e: ConnectException){
                Log.e("sdsd",""+e)
            }
    }
    private fun initBasicSetup(){
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Chicken Biriyani"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Chef: Soumen"

        // Just initialize recyclerview
        initOngoingRecyclerView()

        // initialize All filter button listener
        initFilterListener()

        // Load  Ongoing Dish Recycler View with data
        loadOngoingDishRecyclerView()
}
    private fun initOngoingRecyclerView() {
        recyclerView_ongoing_buyer_dishes?.apply {
            layoutManager = LinearLayoutManager(activity)
            mHomeFeedAdapter = HomeFeedAdapter()
            adapter = mHomeFeedAdapter
        }
    }
    private fun initFilterListener() {
        tv_filter_all.setOnClickListener(this)
        tv_filter_chef.setOnClickListener(this)
        tv_filter_veg.setOnClickListener(this)
        tv_filter_non_veg.setOnClickListener(this)
        tv_filter_all.setOnClickListener(this)
        tv_filter_breakfast.setOnClickListener(this)
        tv_filter_lunch.setOnClickListener(this)
        tv_filter_dinner.setOnClickListener(this)
        tv_filter_snacks.setOnClickListener(this)
    }
    private fun loadOngoingDishRecyclerView() {
        mHomeFeedAdapter.submitOngoingDishData(mHomeFeedInstance,"Ongoing")
    }
    private fun reLoadOngoingDishRecyclerView(dishes: List<Dish>) {
        val filteredData = HomeFeedResponse(mHomeFeedInstance.chefs, dishes, mHomeFeedInstance.tomorrowsDishes, mHomeFeedInstance.inactiveDishes )
        mHomeFeedAdapter.submitOngoingDishData(filteredData,"Ongoing")
    }

}
