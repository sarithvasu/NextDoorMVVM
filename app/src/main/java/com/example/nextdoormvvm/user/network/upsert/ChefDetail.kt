package com.example.nextdoormvvm.user.network.upsert

import com.example.nextdoormvvm.internal.BusinessObject

data class ChefDetail (
    val chefBio: ChefBio,
    val documentProof: DocumentProof
): BusinessObject()


data class ChefBio(
    val ChefId: Int,
    val UserId: Int,
    val AboutChef: String,
    val SpecializedOption : Int
)

data class DocumentProof(
    val ApartmentId: Int,
    val FlatNumber: String,
    val FullName: String,
    val IdProofUrl: String,
    val AddressProofUrl: String
)