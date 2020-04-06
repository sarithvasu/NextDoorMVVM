package com.example.nextdoormvvm.seller.ui.account

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.internal.lazyDeferred
import com.example.nextdoormvvm.seller.repository.SellerRepository


class AccountViewModel (private val sellerRepository: SellerRepository, private val queryParameterMap: Map<String, String>): ViewModel() {

    val ledgerFeed by lazyDeferred {
        sellerRepository.fetchLedgerFeed(queryParameterMap)
    }

}
