package com.example.nextdoormvvm.user.ui.registerbuyer

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.network.post.PostBuyer
import com.example.nextdoormvvm.user.repository.UserRepository


class RegisterBuyerViewModel(private val userRepository: UserRepository, businessObject: BusinessObject): ViewModel() {

    val insertBuyer by lazyDeferred {
        userRepository.postBuyer(businessObject as PostBuyer)
    }
}
