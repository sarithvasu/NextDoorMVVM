package com.example.nextdoormvvm.seller.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ChefOrdersResponse(
    val otherDdayOrder: List<SellerOrder>,
    val todaysOrder: List<SellerOrder>,
    val tomorrowsOrder: List<SellerOrder>,
    val requestedOrders: List<SellerOrder>
) : Parcelable


@Parcelize
data class SellerOrder(
    val BuyerFlatNumber: String,
    val BuyerName: String,
    val DeliveryCharge: Int,
    val DeliveryEndTime: String,
    val DeliveryOptions: Int,
    val DeliveryStartTime: String,
    val DishId: Int,
    val DishName: String,
    val GroupId: Int,
    val UnitPrice:Int,
    val OrderDate: String,
    val OrderId: Int,
    val OrderStatus: String,
    val OrderTotal: Int,
    val PackingCharge: Int,
    val PackingOptions: Int,
    val PaymentMode: String,
    val OrderQuantity: Int
) : Parcelable