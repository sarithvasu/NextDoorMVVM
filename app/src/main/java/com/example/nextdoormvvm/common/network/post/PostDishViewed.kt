package com.example.nextdoormvvm.common.network.post

import com.example.nextdoormvvm.internal.BusinessObject

data class PostDishViewed(
    var UserId: Int,
    var ChefId: Int,
    var DishId: Int,
    var ViewedStartTime: String,
    var ViewedEndTime: String
) : BusinessObject()