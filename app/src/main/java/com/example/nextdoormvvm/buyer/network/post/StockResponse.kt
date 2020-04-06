package com.example.nextdoormvvm.buyer.network.post

import com.example.nextdoormvvm.internal.BusinessObject


/* need to change to other package since it will come under get*/

data class StockResponse(
    val DishId: Int,
    val RequestValue: String,
    val StockValue: String
): BusinessObject()