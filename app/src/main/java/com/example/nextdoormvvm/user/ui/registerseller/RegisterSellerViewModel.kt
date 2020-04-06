package com.example.nextdoormvvm.user.ui.registerseller

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.network.upsert.ChefDetail
import com.example.nextdoormvvm.user.repository.UserRepository


class RegisterSellerViewModel(private val userRepository: UserRepository, businessObject: BusinessObject): ViewModel() {

    val upsertChefDetail by lazyDeferred {
        userRepository.upsertChefDetail(businessObject as ChefDetail)
    }

}