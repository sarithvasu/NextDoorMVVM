package com.example.nextdoormvvm.user.repository

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.user.network.post.PostBuyer
import com.example.nextdoormvvm.user.network.post.PostNewApartment
import com.example.nextdoormvvm.user.network.upsert.ChefDetail
import com.example.nextdoormvvm.user.network.put.Address
import com.example.nextdoormvvm.user.network.response.ApartmentListResponse
import com.example.nextdoormvvm.user.network.response.UserInfoResponse


interface UserRepository {
    suspend fun fetchUserInfo(queryParameterMap: Map<String, String>): LiveData<out UserInfoResponse>
    suspend fun fetchUserInfoFromDB(queryParameterMap: Map<String, String>): UserInfoResponse
    suspend fun updateInfoAtDB(columnName: String, columnValue: String)


    suspend fun fetchApartmentList(queryParameterMap: Map<String, String>): LiveData<out ApartmentListResponse>


    // POST-------POST-----POST-----POST-----POST----POST-------POST-----POST-----POST-----POST
    suspend fun postNewApartment(postNewApartment: PostNewApartment): HttpResponseMessage
    suspend fun postBuyer (postBuyer: PostBuyer): HttpResponseMessage
    suspend fun upsertChefDetail (chefDetail: ChefDetail): HttpResponseMessage


    // PUT-------PUT-----PUT-----PUT-----PUT----PUT-------PUT-----PUT-----PUT-----PUT
    suspend fun updateProfile (queryParameterMap: Map<String, String>): HttpResponseMessage
    suspend fun updateAddress (address: Address): HttpResponseMessage

}