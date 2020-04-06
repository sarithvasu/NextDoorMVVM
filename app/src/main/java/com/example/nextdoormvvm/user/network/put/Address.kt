package com.example.nextdoormvvm.user.network.put

import com.example.nextdoormvvm.internal.BusinessObject

data class Address(
    var NewApartmentId: Int,
    var UserId: Int,
    var UserTypeId: Int,
    var NewFlatNumber: String,
    var AddressProofUrl: String? = null
) : BusinessObject()