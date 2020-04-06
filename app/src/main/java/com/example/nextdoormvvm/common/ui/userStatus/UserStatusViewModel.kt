package com.example.nextdoormvvm.common.ui.userStatus

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.common.repository.CommonRepository
import com.example.nextdoormvvm.internal.lazyDeferred

class UserStatusViewModel(private val repository: CommonRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val userStatus by lazyDeferred {
        repository.fetchUserStatus (queryParameterMap)
    }

    val acceptOrderByOrderIds by lazyDeferred {
        repository.acceptOrderByOrderIds(queryParameterMap)
    }
    val updateDeliveryStatusByOrderIds by lazyDeferred {
        repository.updateDeliveryStatusByOrderIds(queryParameterMap)
    }
    val rejectRequestedOrderByOrderId by lazyDeferred {
        repository.rejectRequestedOrderByOrderId(queryParameterMap)
    }
}