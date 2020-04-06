package com.example.nextdoormvvm.common.ui.orderhistory

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.common.repository.CommonRepository
import com.example.nextdoormvvm.internal.lazyDeferred

class OrderHistoryViewModel (private val repository: CommonRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val orderHistory by lazyDeferred {
        repository.fetchOrderHistory(queryParameterMap)
    }
}
