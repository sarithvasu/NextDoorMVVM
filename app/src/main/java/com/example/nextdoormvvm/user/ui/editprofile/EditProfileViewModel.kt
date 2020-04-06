package com.example.nextdoormvvm.user.ui.editprofile

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.repository.UserRepository

class EditProfileViewModel(private val userRepository: UserRepository, private val queryParameterMap: Map<String, String>) : ViewModel() {
    val fetchUserInfoFromDB by lazyDeferred {
        userRepository.fetchUserInfoFromDB(queryParameterMap)
    }
}
