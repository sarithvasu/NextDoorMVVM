package com.example.nextdoormvvm.buyer.network.response

import com.example.nextdoormvvm.internal.BusinessObject

data class CartItem(
    val DishId: Int,
    val Quantity: Int,
    val DishAvailableEndTime: String

):BusinessObject()