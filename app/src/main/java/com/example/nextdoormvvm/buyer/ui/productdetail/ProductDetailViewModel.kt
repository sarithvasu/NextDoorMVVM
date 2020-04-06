package com.example.nextdoormvvm.buyer.ui.productdetail

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.buyer.network.post.PostSharedDishDetail
import com.example.nextdoormvvm.buyer.repository.BuyerRepository
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred


@Suppress("UNCHECKED_CAST")
class ProductDetailViewModel(private val buyerRepository: BuyerRepository, private val businessObject: BusinessObject): ViewModel() {

    val insertSharedDishDetail by lazyDeferred {
        buyerRepository.postSharedDishDetail(businessObject as PostSharedDishDetail)
    }

}


