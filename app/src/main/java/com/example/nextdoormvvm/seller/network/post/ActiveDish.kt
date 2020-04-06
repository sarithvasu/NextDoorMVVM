package com.example.nextdoormvvm.seller.network.post

import com.example.nextdoormvvm.internal.BusinessObject

data class ActiveDish(
    val DishId: Int,
    val UserId:Int,
    val ChefId: Int,
    val DeliveryCharge: Int,
    val DeliveryOptions: Int,
    val DishAvailableEndTime: String,
    val DishAvailableStartTime: String,
    val PackingCharge: Int,
    val PackingOptions: Int,
    val QuantityPreparing: Int,
    val TimeSlotInterval: Int
):BusinessObject()