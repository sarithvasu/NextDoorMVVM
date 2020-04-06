package com.example.nextdoormvvm.user.network.post

import com.example.nextdoormvvm.internal.BusinessObject

data class PostBuyer(
    val UserTypeId: Int,
    val ApartmentId: Int,
    val FullName: String,
    val FlatNumber: String,
    val MobileNumber: String,
    val IsMobileNumberVerified: Boolean,
    val Email: String,
    val IsEmailVerified: Boolean,
    val Gender: String,
    val ProfileImageUrl: String
) : BusinessObject()








