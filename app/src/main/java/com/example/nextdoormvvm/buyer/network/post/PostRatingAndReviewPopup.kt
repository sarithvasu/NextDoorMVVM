package com.example.nextdoormvvm.buyer.network.post

import com.example.nextdoormvvm.internal.BusinessObject

data class PostRatingAndReviewPopup(
    val OrderId: Int?,
    val ChefId: Int?,
    var DishId: Int?,
    var BuyerId: Int?,
    var ReviewTagId: Int,
    var ReviewNote: String,
    var StarRatings: Int
) : BusinessObject()


