package com.example.nextdoormvvm.user.ui.otp

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.repository.UserRepository

class VerifyOtpViewModel(private val userRepository: UserRepository, private val queryParameterMap: Map<String, String>) : ViewModel() {

    val fetchUserInfoByMobile by lazyDeferred {
        userRepository.fetchUserInfo(queryParameterMap)
    }

}
