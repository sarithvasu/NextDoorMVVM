package com.example.nextdoormvvm.buyer.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class HomeFeedResponse(
        val chefs: List<Chef>,
        var ongoingDishes: List<Dish>,
        var tomorrowsDishes: List<Dish>,
        var inactiveDishes: List<InactiveDish>
) : Parcelable

@Parcelize
class Chef(
        val ChefId: Int,
        val SellerUserId: Int,
        val SellerPayeeId: Int,
        val FullName: String,
        val FlatNumber: String,
        val Gender: String,
        val VegCount: String,
        val NonVegCount: String,
        val ActiveDishCount: String,
        val ChefAverageRating: Float,
        val ChefTotalDishReview: Int,
        val ChefFollowerCount: Int,
        val AboutChef: String,
        val IsSpecializedInVeg: Boolean,
        val IsSpecializedInNonVeg: Boolean,
        val IsSpecializedInBoth: Boolean,
        val ProfileImageUrl: String,
        val testimonials: List<Testimonial>,
        val signatureDishes: List<SignatureDish>,
        val chefFollowers: List<ChefFollower>
) : Parcelable


@Parcelize
data class Testimonial(
        val DishName: String,
        val ReviewerName: String,
        val ReviewerFlatNumber: String,
        val ReviewNote: String
) : Parcelable


@Parcelize
data class SignatureDish(
        val ChefId: Int,
        val DishId: String,
        val DishImageUrl: String,
        val DishName: String,
        val DishAverageRating: Int,
        val DishType: String,
        val UnitPrice: Int,
        val IsActiveNow: Boolean
) : Parcelable



@Parcelize
data class Dish(
        val AvailableQuantity: Int,
        val ChefId: Int,
        val SellerUserId :Int,
        val DeliveryCharge: Int,
        val DeliveryOptions: Int,
        val DishAvailableEndTime: String,
        val DishAvailableStartTime: String,
        val DishAverageRating: Int,
        val DishDescription: String,
        val DishId: Int,
        val DishImageUrl: String,
        val DishName: String,
        val DishReviewedCount: Int,
        val DishSold: Int,
        val DishTotalReview: Int,
        val DishType: String,
        val MealType: String,
        val PackingCharge: Int,
        val PackingOptions: Int,
        val ReviewCount: Int,
        val ServingsPerPlate: Int,
        val SharedCount: Int,
        val TimeSlotInterval: Int,
        val UnitPrice: Int
) : Parcelable


@Parcelize
data class InactiveDish(
        val ChefId: Int,
        val SellerUserId :Int,
        val DeliveryCharge: Int,
        val DeliveryOptions: Int,
        val TimeSlotInterval:Int,
        val DishDescription: String,
        val DishId: Int,
        val DishImageUrl: String,
        val DishName: String,
        val DishType: String,
        val PackingCharge: Int,
        val PackingOptions: Int,
        val ServingsPerPlate: Int,
        val UnitPrice: Int
): Parcelable


@Parcelize
data class ChefFollower(
        val ChefId: Int,
        val FollowerUserId: Int,
        val IsFollowing: Boolean
): Parcelable
