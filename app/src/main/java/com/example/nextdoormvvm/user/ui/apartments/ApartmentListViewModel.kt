package com.example.nextdoormvvm.user.ui.apartments

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.repository.UserRepository


class ApartmentListViewModel(private val userRepository: UserRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val apartmentList by lazyDeferred {
        userRepository.fetchApartmentList(queryParameterMap)
    }
}
