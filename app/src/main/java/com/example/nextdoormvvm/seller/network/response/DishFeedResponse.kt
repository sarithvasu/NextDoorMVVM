package com.example.nextdoormvvm.seller.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DishFeedResponse(
    val ActiveDishFeed: List<DishFeed>,
    val InactiveDishFeed: List<DishFeed>
) : Parcelable

@Parcelize
data class DishFeed(
    val AvailableQuantity: Int,
    val DeliveryCharge: Int,
    val DishAvailableEndTime: String,
    val DishAvailableStartTime: String,
    val DishDescription: String,
    val DishId: Int,
    val DishImageUrl: String,
    val DishName: String,
    val DishType: String,
    val EarningAfterServiceCharge: Int,
    val IsSignatureDish: Boolean,
    val MealType: String,
    val PackingCharge: Int,
    val PackingOptions: Int,
    val PlatformServiceChargePercentage: Int,
    val QuantityPreparing: Int,
    val DeliveryOptions: Int,
    val ServingsPerPlate: Int,
    val TimeSlotInterval: Int,
    val UnitPrice: Int
):Parcelable