package com.example.nextdoormvvm.buyer.network.post

import com.example.nextdoormvvm.internal.BusinessObject


data class ChefFollower (
    val ApartmentId:Int,
    val BuyerUserId: Int,
    val ChefId: Int
): BusinessObject()
