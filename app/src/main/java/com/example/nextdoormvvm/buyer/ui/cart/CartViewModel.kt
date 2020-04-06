package com.example.nextdoormvvm.buyer.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CartViewModel :ViewModel(){
    val cartDetail = MutableLiveData<CartDetail>()

    fun setCartDetail(input: CartDetail){
        cartDetail.value = input
    }

    fun receiveCartDetail(): LiveData<CartDetail> {
        return cartDetail
    }
}