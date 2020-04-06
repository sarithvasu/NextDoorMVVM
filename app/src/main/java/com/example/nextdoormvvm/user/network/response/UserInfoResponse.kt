package com.example.nextdoormvvm.user.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey


const val CURRENT_USER_ID = 0

@Entity(tableName = "user_info")
data class UserInfoResponse(
    val Address: String,
    val ApartmentId: Int,
    val ApartmentName: String,
    val Area: String,
    val ChefId: Int,
    val Email: String,
    val FlatNumber: String,
    val FullName: String,
    val Gender: String,
    val IsActive: Boolean,
    val IsEmailVerified: Boolean,
    val IsMobileNumberVerified: Boolean,
    val MobileNumber: String,
    val PinCode: String,
    val ProfileImageUrl: String,
    val StatusNote: String?,
    val UserId: Int,
    val UserTypeId: Int
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_USER_ID
}