package com.example.nextdoormvvm.buyer.ui.home.checkoutrequest

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.buyer.network.post.Order
import com.example.nextdoormvvm.buyer.repository.BuyerRepository
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred

class CheckoutRequestViewModel(private val buyerRepository: BuyerRepository, private val businessObject: BusinessObject) : ViewModel() {
    val postOrderByRequest by lazyDeferred {
        buyerRepository.postOrderByRequest(businessObject as Order)
    }
}
