package com.example.nextdoormvvm.user.ui.userInfo

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.user.repository.UserRepository

class UserInfoViewModel(private val userRepository: UserRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

        val userInfo by lazyDeferred {
            userRepository.fetchUserInfo(queryParameterMap)
    }


    val userInfoFromDB by lazyDeferred {
        userRepository.fetchUserInfoFromDB(queryParameterMap)
    }

}





