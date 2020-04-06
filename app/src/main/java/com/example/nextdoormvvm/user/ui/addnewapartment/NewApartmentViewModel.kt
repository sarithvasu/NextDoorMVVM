package com.example.nextdoormvvm.user.ui.addnewapartment

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.network.post.PostNewApartment
import com.example.nextdoormvvm.user.repository.UserRepository


class NewApartmentViewModel(private val userRepository: UserRepository, businessObject: BusinessObject): ViewModel() {

    val insertNewApartment by lazyDeferred {
        userRepository.postNewApartment(businessObject as PostNewApartment)
    }

}