package com.example.nextdoormvvm.buyer.network.post

import com.example.nextdoormvvm.internal.BusinessObject


data class PostSharedDishDetail(
    var UserId: Int,
    var DishId: Int,
    var SharedVia: String
)  : BusinessObject()




