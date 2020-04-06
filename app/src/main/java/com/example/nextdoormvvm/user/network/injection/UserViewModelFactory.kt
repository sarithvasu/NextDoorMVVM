package com.example.nextdoormvvm.user.network.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.RepositoryInstance
import com.example.nextdoormvvm.user.ui.addnewapartment.NewApartmentViewModel
import com.example.nextdoormvvm.user.ui.apartments.ApartmentListViewModel
import com.example.nextdoormvvm.user.ui.changeaddress.UpdateAddressViewModel
import com.example.nextdoormvvm.user.ui.editprofile.EditProfileViewModel
import com.example.nextdoormvvm.user.ui.otp.VerifyOtpViewModel
import com.example.nextdoormvvm.user.ui.registerbuyer.RegisterBuyerViewModel
import com.example.nextdoormvvm.user.ui.registerseller.RegisterSellerViewModel
import com.example.nextdoormvvm.user.ui.updateprofile.UpdateProfileViewModel
import com.example.nextdoormvvm.user.ui.userInfo.UserInfoViewModel

class UserViewModelFactory(private val viewModelType: UserViewModelTypeEnum, private val queryParameterMap: Map<String, String>) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (viewModelType) {
            UserViewModelTypeEnum.UserInfoViewModel -> {
                UserInfoViewModel(RepositoryInstance.userRepositoryInstance, queryParameterMap) as T
            }
            UserViewModelTypeEnum.ApartmentListViewModel -> {
                ApartmentListViewModel(RepositoryInstance.userRepositoryInstance, queryParameterMap) as T
            }
            UserViewModelTypeEnum.UpdateProfileViewModel -> {
                UpdateProfileViewModel(RepositoryInstance.userRepositoryInstance, queryParameterMap) as T
            }
            UserViewModelTypeEnum.VerifyOtpViewModel -> {
                VerifyOtpViewModel(RepositoryInstance.userRepositoryInstance, queryParameterMap) as T
            }
            UserViewModelTypeEnum.EditProfileViewModel -> {
                EditProfileViewModel(RepositoryInstance.userRepositoryInstance, queryParameterMap) as T
            }
            else -> {
                viewModel as T
            }
        }
    }
}


class UserViewModelFactoryBO(private val viewModelType: UserViewModelTypeEnum, private val businessObject: BusinessObject) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when (viewModelType) {
            UserViewModelTypeEnum.NewApartmentViewModel -> {
                return NewApartmentViewModel(RepositoryInstance.userRepositoryInstance, businessObject) as T
            }
            UserViewModelTypeEnum.RegisterBuyerViewModel -> {
                return RegisterBuyerViewModel(RepositoryInstance.userRepositoryInstance, businessObject) as T
            }
            UserViewModelTypeEnum.RegisterSellerViewModel -> {
                return RegisterSellerViewModel(RepositoryInstance.userRepositoryInstance, businessObject) as T
            }
            UserViewModelTypeEnum.UpdateAddressViewModel -> {
                return UpdateAddressViewModel(RepositoryInstance.userRepositoryInstance, businessObject) as T
            }
            else -> {
                return viewModel as T
            }
        }
    }
}



