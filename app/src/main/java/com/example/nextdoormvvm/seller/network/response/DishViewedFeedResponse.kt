package com.example.nextdoormvvm.seller.network.response



data class DishViewedFeedResponse(
    val FullName: String,
    val FlatNumber: String,
    val MobileNumber: String,
    val DishName: String,
    val ViewedStartTime: String,
    val ViewedEndTime: String,
    val DurationInSeconds: Int
)