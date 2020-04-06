package com.example.nextdoormvvm.seller.ui.dish.analytics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.seller.network.injection.SellerModelGenerator
import com.example.nextdoormvvm.seller.network.injection.SellerViewModelTypeEnum
import com.example.nextdoormvvm.seller.ui.dish.dishFeed.DishFeedViewModel
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnalyticFragment : Fragment() {

    companion object {
        fun newInstance() = AnalyticFragment()
    }

    private lateinit var viewModel: AnalyticViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.analytic_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindDishViewedFeedData()
       // bindSharedFeedData()
    }



    private fun bindDishViewedFeedData() {
        val queryParameterMap = mapOf("dishId" to "242")
        val model = SellerModelGenerator.getModel(
            this,
            SellerViewModelTypeEnum.AnalyticViewModel,
            queryParameterMap
        ) as AnalyticViewModel
        GlobalScope.launch(Dispatchers.Main) {
            val dishViewedFeedResponse = model.dishViewedFeed.await()
            dishViewedFeedResponse.observe(this@AnalyticFragment, Observer {
                //textView.text = it.InactiveDishFeed.get(0).DishName
               // textView.text = it.toString()
            })
        }
    }



    private fun bindSharedFeedData() {
        val queryParameterMap = mapOf("dishId" to "242")
        val model = SellerModelGenerator.getModel(
            this,
            SellerViewModelTypeEnum.AnalyticViewModel,
            queryParameterMap
        ) as AnalyticViewModel
        GlobalScope.launch(Dispatchers.Main) {
            val sharedFeedFeedResponse = model.sharedFeed.await()
            sharedFeedFeedResponse.observe(this@AnalyticFragment, Observer {
                //textView.text = it.InactiveDishFeed.get(0).DishName
                //textView.text = it.toString()
            })
        }
    }

}
