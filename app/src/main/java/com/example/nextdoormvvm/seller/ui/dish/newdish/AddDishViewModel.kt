package com.example.nextdoormvvm.seller.ui.dish.newdish

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.seller.network.post.Dish
import com.example.nextdoormvvm.seller.repository.SellerRepository


class AddDishViewModel(private val sellerRepository: SellerRepository, businessObject: BusinessObject) : ViewModel() {
   val upsertDish by lazyDeferred {
       sellerRepository.upsertDish(businessObject as Dish)
   }
}
