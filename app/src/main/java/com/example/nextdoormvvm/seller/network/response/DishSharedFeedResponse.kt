package com.example.nextdoormvvm.seller.network.response

data class DishSharedFeedResponse(
    val UserName: String,
    val FlatNumber: String,
    val MobileNumber: String,
    val DishName: String,
    val SharedVia: String,
    val SharedOnDate: String
)