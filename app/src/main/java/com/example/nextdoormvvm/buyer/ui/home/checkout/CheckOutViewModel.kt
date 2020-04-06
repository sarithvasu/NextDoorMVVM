package com.example.nextdoormvvm.buyer.ui.home.checkout

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.buyer.network.post.PostPurchaseOrder
import com.example.nextdoormvvm.buyer.repository.BuyerRepository
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred

class CheckOutViewModel(private val buyerRepository: BuyerRepository,private val businessObject: BusinessObject) : ViewModel() {
    val postPurchaseOrderByUPI by lazyDeferred {
        buyerRepository.postPurchaseOrderByUPI(businessObject as PostPurchaseOrder)
    }

    val postPurchaseOrderByCOD by lazyDeferred {
        buyerRepository.postPurchaseOrderByCOD(businessObject as PostPurchaseOrder)
    }

    val buyerInfoFromDB by lazyDeferred {
        buyerRepository.buyerInfoFromDB()
    }

}
