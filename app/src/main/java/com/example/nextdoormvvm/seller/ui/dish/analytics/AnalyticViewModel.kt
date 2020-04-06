package com.example.nextdoormvvm.seller.ui.dish.analytics

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.seller.repository.SellerRepository


class AnalyticViewModel(private val sellerRepository: SellerRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val dishViewedFeed by lazyDeferred {
        sellerRepository.fetchDishViewedFeed(queryParameterMap)
    }


    val sharedFeed by lazyDeferred {
        sellerRepository.fetchDishSharedFeed(queryParameterMap)
    }
}



