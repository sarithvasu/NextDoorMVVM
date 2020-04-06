package com.example.nextdoormvvm.buyer.ui.home.ongoing

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.buyer.repository.BuyerRepository

class OngoingDishesViewModel(private val buyerRepository: BuyerRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val homeFeed by lazyDeferred {
        buyerRepository.fetchHomeFeed(queryParameterMap)
    }
}




