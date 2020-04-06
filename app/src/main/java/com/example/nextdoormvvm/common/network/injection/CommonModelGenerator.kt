package com.example.nextdoormvvm.common.network.injection

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextdoormvvm.common.ui.launch.LaunchActivityViewModel
import com.example.nextdoormvvm.common.ui.orderhistory.OrderHistoryViewModel
import com.example.nextdoormvvm.common.ui.userStatus.UserStatusViewModel
import com.example.nextdoormvvm.internal.BusinessObject


class CommonModelGenerator : ViewModel() {

    companion object {
        private lateinit var viewModel: ViewModel

        fun getModel(fragment: Fragment, viewModelType: CommonViewModelTypeEnum, queryParameterMap: Map<String, String> ): ViewModel {

            viewModel = when (viewModelType) {
                CommonViewModelTypeEnum.OrderHistoryViewModel -> {
                    val modelFactory = CommonViewModelFactory(CommonViewModelTypeEnum.OrderHistoryViewModel, queryParameterMap)
                    ViewModelProvider(fragment,modelFactory).get(OrderHistoryViewModel::class.java)
                }
                CommonViewModelTypeEnum.UserStatusViewModel -> {
                    val modelFactory = CommonViewModelFactory(CommonViewModelTypeEnum.UserStatusViewModel, queryParameterMap)
                    ViewModelProvider(fragment,modelFactory).get(UserStatusViewModel::class.java)
                }
                else -> throw IllegalArgumentException("Wrong ViewModel Type")
            }

            return viewModel

        }


        fun getModelNewNew(fragment: Fragment, viewModelType: CommonViewModelTypeEnum, businessObjects: ArrayList<BusinessObject>): ViewModel {

            when (viewModelType) {
                CommonViewModelTypeEnum.LaunchActivityViewModel -> {
                    val modelFactory = CommonViewModelFactoryBOList(CommonViewModelTypeEnum.LaunchActivityViewModel, businessObjects)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(LaunchActivityViewModel::class.java)
                }
                else -> throw IllegalArgumentException("Wrong ViewModel Type")
            }

            return viewModel
        }

    }
}
