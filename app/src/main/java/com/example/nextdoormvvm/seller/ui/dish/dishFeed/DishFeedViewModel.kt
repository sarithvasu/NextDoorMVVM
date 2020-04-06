package com.example.nextdoormvvm.seller.ui.dish.dishFeed

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.seller.repository.SellerRepository


class DishFeedViewModel(private val sellerRepository: SellerRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val dishFeed by lazyDeferred {
        sellerRepository.fetchDishFeed(queryParameterMap)
    }



}
