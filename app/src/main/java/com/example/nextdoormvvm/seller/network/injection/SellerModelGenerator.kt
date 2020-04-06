package com.example.nextdoormvvm.seller.network.injection

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.seller.ui.account.AccountViewModel
import com.example.nextdoormvvm.seller.ui.dashboard.DashboardViewModel
import com.example.nextdoormvvm.seller.ui.dish.activedish.ActiveDishViewModel
import com.example.nextdoormvvm.seller.ui.dish.analytics.AnalyticViewModel
import com.example.nextdoormvvm.seller.ui.dish.dishFeed.DishFeedViewModel
import com.example.nextdoormvvm.seller.ui.dish.newdish.AddDishViewModel


class SellerModelGenerator : ViewModel() {

    companion object {
        private lateinit var viewModel: ViewModel

        fun getModel(fragment: Fragment, viewModelType: SellerViewModelTypeEnum, queryParameterMap: Map<String, String>): ViewModel {

            when (viewModelType) {
                SellerViewModelTypeEnum.DishFeedViewModel -> {
                    val modelFactory = SellerViewModelFactory(SellerViewModelTypeEnum.DishFeedViewModel, queryParameterMap)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(DishFeedViewModel::class.java)
                }
                SellerViewModelTypeEnum.DashboardViewModel -> {
                    val modelFactory = SellerViewModelFactory(SellerViewModelTypeEnum.DashboardViewModel, queryParameterMap)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(DashboardViewModel::class.java)
                }
                SellerViewModelTypeEnum.AccountViewModel -> {
                    val modelFactory = SellerViewModelFactory(SellerViewModelTypeEnum.AccountViewModel, queryParameterMap)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(AccountViewModel::class.java)
                }
                SellerViewModelTypeEnum.AnalyticViewModel -> {
                    val modelFactory = SellerViewModelFactory(SellerViewModelTypeEnum.AnalyticViewModel, queryParameterMap)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(AnalyticViewModel::class.java)
                }
                else -> throw IllegalArgumentException("Wrong ViewModel Type")
            }

            return viewModel

        }




        /// POST-------------------------------------------------POST
        fun getModel(fragment: Fragment, viewModelType: SellerViewModelTypeEnum, businessObject: BusinessObject): ViewModel {

            if(viewModelType == SellerViewModelTypeEnum.AddDishViewModel){
                val modelFactory = SellerViewModelFactoryBO(SellerViewModelTypeEnum.AddDishViewModel, businessObject)
                viewModel = ViewModelProvider(fragment,modelFactory).get(
                    AddDishViewModel::class.java)
            }
            else if(viewModelType == SellerViewModelTypeEnum.ActiveDishViewModel){
                val modelFactory = SellerViewModelFactoryBO(SellerViewModelTypeEnum.ActiveDishViewModel, businessObject)
                viewModel = ViewModelProvider(fragment,modelFactory).get(
                    ActiveDishViewModel::class.java)
            }

            return viewModel
        }
    }




}