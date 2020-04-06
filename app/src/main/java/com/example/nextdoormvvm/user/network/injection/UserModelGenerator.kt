package com.example.nextdoormvvm.user.network.injection

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.user.ui.addnewapartment.NewApartmentViewModel
import com.example.nextdoormvvm.user.ui.apartments.ApartmentListViewModel
import com.example.nextdoormvvm.user.ui.changeaddress.UpdateAddressViewModel
import com.example.nextdoormvvm.user.ui.editprofile.EditProfileViewModel
import com.example.nextdoormvvm.user.ui.otp.VerifyOtpViewModel
import com.example.nextdoormvvm.user.ui.registerbuyer.RegisterBuyerViewModel
import com.example.nextdoormvvm.user.ui.registerseller.RegisterSellerViewModel
import com.example.nextdoormvvm.user.ui.updateprofile.UpdateProfileViewModel
import com.example.nextdoormvvm.user.ui.userInfo.UserInfoViewModel

class UserModelGenerator : ViewModel() {

    companion object {
        private lateinit var viewModel: ViewModel

        fun getModel(fragment: Fragment, userViewModelType: UserViewModelTypeEnum,queryParameterMap: Map<String, String> ): ViewModel {

            viewModel = when (userViewModelType) {
                UserViewModelTypeEnum.UserInfoViewModel -> {
                    val modelFactory = UserViewModelFactory(UserViewModelTypeEnum.UserInfoViewModel, queryParameterMap)
                    ViewModelProvider(fragment,modelFactory).get(UserInfoViewModel::class.java)
                }
                UserViewModelTypeEnum.ApartmentListViewModel -> {
                    val modelFactory = UserViewModelFactory(UserViewModelTypeEnum.ApartmentListViewModel, queryParameterMap)
                    ViewModelProvider(fragment,modelFactory).get(ApartmentListViewModel::class.java)
                }
                UserViewModelTypeEnum.UpdateProfileViewModel -> {
                    val modelFactory = UserViewModelFactory(UserViewModelTypeEnum.UpdateProfileViewModel, queryParameterMap)
                    ViewModelProvider(fragment,modelFactory).get(UpdateProfileViewModel::class.java)
                }
                UserViewModelTypeEnum.VerifyOtpViewModel -> {
                    val modelFactory = UserViewModelFactory(UserViewModelTypeEnum.VerifyOtpViewModel, queryParameterMap)
                    ViewModelProvider(fragment,modelFactory).get(VerifyOtpViewModel::class.java)
                }
                UserViewModelTypeEnum.EditProfileViewModel -> {
                    val modelFactory = UserViewModelFactory(UserViewModelTypeEnum.EditProfileViewModel, queryParameterMap)
                    ViewModelProvider(fragment,modelFactory).get(EditProfileViewModel::class.java)
                }
                else -> throw IllegalArgumentException("Wrong ViewModel Type")
            }

            return this.viewModel

        }






        fun getModel(fragment: Fragment, userViewModelType: UserViewModelTypeEnum, businessObject: BusinessObject): ViewModel {

            when (userViewModelType) {
                UserViewModelTypeEnum.NewApartmentViewModel -> {
                    val modelFactory = UserViewModelFactoryBO(UserViewModelTypeEnum.NewApartmentViewModel, businessObject)
                    this.viewModel = ViewModelProvider(fragment,modelFactory).get(NewApartmentViewModel::class.java)
                }
                UserViewModelTypeEnum.RegisterBuyerViewModel -> {
                    val modelFactory = UserViewModelFactoryBO(UserViewModelTypeEnum.RegisterBuyerViewModel, businessObject)
                    this.viewModel = ViewModelProvider(fragment,modelFactory).get(RegisterBuyerViewModel::class.java)
                }
                UserViewModelTypeEnum.RegisterSellerViewModel -> {
                    val modelFactory = UserViewModelFactoryBO(UserViewModelTypeEnum.RegisterSellerViewModel, businessObject)
                    this.viewModel = ViewModelProvider(fragment,modelFactory).get(RegisterSellerViewModel::class.java)
                }
                UserViewModelTypeEnum.UpdateAddressViewModel -> {
                    val modelFactory = UserViewModelFactoryBO(UserViewModelTypeEnum.UpdateAddressViewModel, businessObject)
                    this.viewModel = ViewModelProvider(fragment,modelFactory).get(UpdateAddressViewModel::class.java)
                }
                else -> throw IllegalArgumentException("Wrong ViewModel Type")
            }

            return viewModel
        }


    }
}