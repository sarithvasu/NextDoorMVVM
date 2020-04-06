package com.example.nextdoormvvm.buyer.network.post

import com.example.nextdoormvvm.internal.BusinessObject

data class Testimonial (
    val ChefId: Int,
    val ChefUserId: Int,
    val ReviewerUserId:Int,
    val TestimonialNote: String
): BusinessObject()
