package com.example.nextdoormvvm.user.ui.changeaddress

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.network.put.Address
import com.example.nextdoormvvm.user.repository.UserRepository

class UpdateAddressViewModel(private val userRepository: UserRepository, businessObject: BusinessObject): ViewModel() {

    val updateAddress by lazyDeferred {
        userRepository.updateAddress(businessObject as Address)
    }

}