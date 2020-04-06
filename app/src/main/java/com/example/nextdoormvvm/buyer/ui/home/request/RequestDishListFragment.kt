package com.example.nextdoormvvm.buyer.ui.home.request

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.buyer.ui.home.adapter.RequestDishListAdapter
import com.example.nextdoormvvm.buyer.ui.home.ongoing.OngoingDishesViewModel
import com.example.nextdoormvvm.internal.ScopedFragment
import kotlinx.android.synthetic.main.request_dish_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ConnectException

class RequestDishListFragment : ScopedFragment() {




    companion object {
        fun newInstance() = RequestDishListFragment()
    }

    private lateinit var mRequestDishAdapter: RequestDishListAdapter
    private lateinit var mHomeFeedInstance: HomeFeedResponse
    private lateinit var viewModel: RequestDishViewModel
    private lateinit var mViewModel: OngoingDishesViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       return inflater.inflate(R.layout.request_dish_fragment, container, false)
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val queryParameterMap = mapOf("ApartmentId" to "1")
        mViewModel = BuyerModelGenerator.getModel(this,BuyerViewModelTypeEnum.OngoingDishesViewModel, queryParameterMap ) as OngoingDishesViewModel
        getHomeFeedDataApi()

    }

    private fun getHomeFeedDataApi() = launch(Dispatchers.Main) {
        try {
            val currentWeather = mViewModel.homeFeed.await()
            currentWeather.observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer

                request_dish_loading.visibility = View.GONE
                mHomeFeedInstance = it
                initBasicSetup()

            })
        }
        catch (e: ConnectException){
            Log.e("sdsd",""+e)
        }
    }

    private fun initBasicSetup() {
        initRequestDishRecyclerView()
        loadOngoingDishRecyclerView()
    }

    private fun initRequestDishRecyclerView() {
        recyclerView_request_buyer_dishes?.apply {
            layoutManager = LinearLayoutManager(activity)
            mRequestDishAdapter = RequestDishListAdapter()
            adapter = mRequestDishAdapter
        }
    }
    private fun loadOngoingDishRecyclerView() {
        mRequestDishAdapter.submitRequestDishData(mHomeFeedInstance)
    }


}