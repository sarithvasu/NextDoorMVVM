package com.example.nextdoormvvm.buyer.network.response

data class VerifyDish(
    val DishId: Int,
    val RequestValue: String,
    val StockValue: String
)