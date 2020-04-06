package com.example.nextdoormvvm.seller.ui.dish.activedish

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.seller.network.post.ActiveDish
import com.example.nextdoormvvm.seller.repository.SellerRepository

class ActiveDishViewModel(private val sellerRepository: SellerRepository, businessObject: BusinessObject) : ViewModel() {
    val upsertActiveDish by lazyDeferred {
        sellerRepository.upsertActiveDish(businessObject as ActiveDish)
    }
}
