package com.example.nextdoormvvm.common.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class OrderHistoryResponse(
    val OrderSummary: List<OrderSummary>,
    val Reviews: List<Reviews>
): Parcelable


@Parcelize
data class OrderSummary(

    val BuyerFlatNumber: String?,
    val BuyerId: Int?,
    val BuyerName: String?,
    val ChefFlatNumber: String?,
    val ChefId: Int?,
    val ChefName: String?,
    val DeliveryCharge: Int,
    val DeliveryEndTime: String,
    val DeliveryOptions: Int,
    val DeliveryStartTime: String,
    val DishId: Int,
    val DishName: String,
    val GroupId: Int,
    val ItemTotal: Int,
    val MarkedAsOrderDeliveredDate: String,
    val OrderDate: String,
    val OrderId: Int,
    val OrderStatus: String,
    val OrderTotal: Int,
    val PackingCharge: Int,
    val PackingOptions: Int,
    val PaymentMode: String,
    val OrderQuantity: Int,
    val TotalDeliveryCharge: Int,
    val TotalPackingCharge: Int,
    val UnitPrice: Int
):Parcelable


@Parcelize
data class Reviews(
    val BuyerId: Int,
    val BuyerName: String,
    val DishId: Int,
    val Ratings: Int,
    val ReviewNote: String,
    val ReviewedDate: String,
    val TagId: Int,
    val TagName: String
):Parcelable