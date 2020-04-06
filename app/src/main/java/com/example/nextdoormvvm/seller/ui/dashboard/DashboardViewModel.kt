package com.example.nextdoormvvm.seller.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.seller.repository.SellerRepository


class DashboardViewModel (private val sellerRepository: SellerRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val chefOrders by lazyDeferred {
        sellerRepository.fetchChefSalesOrders(queryParameterMap)
    }

}

