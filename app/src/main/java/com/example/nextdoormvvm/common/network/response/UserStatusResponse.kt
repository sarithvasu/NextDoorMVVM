package com.example.nextdoormvvm.common.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserStatusResponse(
    val buyerStatus: BuyerStatus?,
    val chefStatus: ChefStatus?
): Parcelable


@Parcelize
data class BuyerStatus(
    val Profile: String?,
    val Status: String?,
    val PendingReviewList: List<UnresolvedStatus>?
):Parcelable

@Parcelize
data class ChefStatus(
    val Profile: String?,
    val Status: String?,
    val AdminNote: String?,
    val PlatformServiceChargePercentage: Double?,
    val PendingDeliveryList: List<UnresolvedStatus>?,
    val PendingRequestList: List<UnresolvedStatus>?
):Parcelable

@Parcelize
data class UnresolvedStatus(
    val OrderId: Int?,
    val ChefId: Int?,
    var DishId: Int?,
    val DishName: String?,
    val ChefName: String?,
    val BuyerName: String?,
    val ButtonText: String?,
    val OrderQuantity: Int?,
    val DeliveryDate: String?
):Parcelable





