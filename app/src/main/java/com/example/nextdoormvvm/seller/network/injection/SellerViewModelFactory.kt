package com.example.nextdoormvvm.seller.network.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.RepositoryInstance
import com.example.nextdoormvvm.seller.ui.account.AccountViewModel
import com.example.nextdoormvvm.seller.ui.dashboard.DashboardViewModel
import com.example.nextdoormvvm.seller.ui.dish.activedish.ActiveDishViewModel
import com.example.nextdoormvvm.seller.ui.dish.analytics.AnalyticViewModel
import com.example.nextdoormvvm.seller.ui.dish.dishFeed.DishFeedViewModel
import com.example.nextdoormvvm.seller.ui.dish.newdish.AddDishViewModel

class SellerViewModelFactory(private val viewModelType: SellerViewModelTypeEnum, private val queryParameterMap: Map<String, String>) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when (viewModelType) {
            SellerViewModelTypeEnum.DishFeedViewModel -> {
                return DishFeedViewModel(RepositoryInstance.sellerRepositoryInstance,queryParameterMap ) as T
            }
            SellerViewModelTypeEnum.DashboardViewModel -> {
                return DashboardViewModel(RepositoryInstance.sellerRepositoryInstance,queryParameterMap) as T
            }
            SellerViewModelTypeEnum.AccountViewModel -> {
                return AccountViewModel(RepositoryInstance.sellerRepositoryInstance,queryParameterMap) as T
            }
            SellerViewModelTypeEnum.AnalyticViewModel -> {
                return AnalyticViewModel(RepositoryInstance.sellerRepositoryInstance,queryParameterMap) as T
            }
            else -> {
                return viewModel as T
            }
        }

    }

}


class SellerViewModelFactoryBO(private val viewModelType: SellerViewModelTypeEnum, private val businessObject: BusinessObject) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (viewModelType) {
            SellerViewModelTypeEnum.AddDishViewModel -> {
                AddDishViewModel(RepositoryInstance.sellerRepositoryInstance,businessObject ) as T
            }
            SellerViewModelTypeEnum.ActiveDishViewModel -> {
                ActiveDishViewModel( RepositoryInstance.sellerRepositoryInstance, businessObject  ) as T
            }
            else -> {
                viewModel as T
            }
        }
    }

}















