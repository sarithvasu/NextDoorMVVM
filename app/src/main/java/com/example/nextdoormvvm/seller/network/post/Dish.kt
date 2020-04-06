package com.example.nextdoormvvm.seller.network.post

import com.example.nextdoormvvm.internal.BusinessObject

data class Dish(
    val DishId:Int,
    val CategoryId: Int,
    val UserId: Int,
    val ChefId: Int,
    val DishDescription: String,
    val DishImageUrl: String,
    val DishName: String,
    val DishType: String,
    val EarningAfterServiceCharges: Int,
    val MealType: String,
    val IsSignatureDish: Boolean,
    val ServingsPerPlate: Int,
    val SubCategoryId: Int,
    val UnitPrice: Int

):BusinessObject()