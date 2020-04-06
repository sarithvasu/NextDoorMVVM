package com.example.nextdoormvvm.buyer.ui.home.checkout

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.buyer.network.response.CartItem
import com.example.nextdoormvvm.buyer.repository.BuyerRepository
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred


/* we have fetch and post is there in checkout fragement that why we added a new viewmodel */
@Suppress("UNCHECKED_CAST")
class CheckOutStockViewModel(private val buyerRepository: BuyerRepository, private val businessObjects: ArrayList<BusinessObject>) : ViewModel() {
    val fetchStock by lazyDeferred {
        buyerRepository.fetchStock(businessObjects as ArrayList<CartItem>)
    }
}


