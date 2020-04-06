package com.example.nextdoormvvm.user.ui.updateprofile

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.repository.UserRepository


class UpdateProfileViewModel(private val userRepository: UserRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val updateProfile by lazyDeferred {
        userRepository.updateProfile(queryParameterMap)
    }
}