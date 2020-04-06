package com.example.nextdoormvvm.buyer.ui.chef.chefdetail

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.buyer.network.post.ChefFollower
import com.example.nextdoormvvm.buyer.repository.BuyerRepository
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred


@Suppress("UNCHECKED_CAST")
class ChefDetailViewModel(private val buyerRepository: BuyerRepository, private val businessObject: BusinessObject): ViewModel() {

    val postChefFollower by lazyDeferred {
        buyerRepository.postChefFollower(businessObject as ChefFollower)
    }


    val updateChefFollower by lazyDeferred {
        buyerRepository.updateChefFollower(businessObject as ChefFollower)
    }
}