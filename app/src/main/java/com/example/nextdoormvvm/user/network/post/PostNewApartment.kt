package com.example.nextdoormvvm.user.network.post

import com.example.nextdoormvvm.internal.BusinessObject

data class PostNewApartment(
    val ApartmentName: String,
    var Address: String,
    val Area: String,
    val PinCode: String,
    val RequesterFullName: String,
    val RequesterPhoneNumber: String
) : BusinessObject()