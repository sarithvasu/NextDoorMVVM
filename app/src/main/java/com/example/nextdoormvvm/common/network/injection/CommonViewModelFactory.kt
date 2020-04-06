package com.example.nextdoormvvm.common.network.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextdoormvvm.common.ui.launch.LaunchActivityViewModel
import com.example.nextdoormvvm.common.ui.orderhistory.OrderHistoryViewModel
import com.example.nextdoormvvm.common.ui.userStatus.UserStatusViewModel
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.RepositoryInstance


class CommonViewModelFactory(private val viewModelType: CommonViewModelTypeEnum, private val queryParameterMap: Map<String, String>) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (viewModelType) {
            CommonViewModelTypeEnum.OrderHistoryViewModel -> {
                OrderHistoryViewModel(RepositoryInstance.commonRepositoryInstance,queryParameterMap ) as T
            }
            CommonViewModelTypeEnum.UserStatusViewModel -> {
                UserStatusViewModel( RepositoryInstance.commonRepositoryInstance,queryParameterMap ) as T
            }
            else -> {
                viewModel as T
            }
        }
    }

}



class CommonViewModelFactoryBOList(private val viewModelType: CommonViewModelTypeEnum, private val businessObjects: ArrayList<BusinessObject>) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (viewModelType) {
            CommonViewModelTypeEnum.LaunchActivityViewModel -> {
                LaunchActivityViewModel(RepositoryInstance.commonRepositoryInstance,businessObjects ) as T
            }
            else -> {
                viewModel as T
            }
        }
    }

}