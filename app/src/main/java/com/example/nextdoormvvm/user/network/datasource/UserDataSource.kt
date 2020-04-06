package com.example.nextdoormvvm.user.network.datasource

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.user.network.post.PostBuyer
import com.example.nextdoormvvm.user.network.post.PostNewApartment
import com.example.nextdoormvvm.user.network.upsert.ChefDetail
import com.example.nextdoormvvm.user.network.put.Address
import com.example.nextdoormvvm.user.network.response.ApartmentListResponse
import com.example.nextdoormvvm.user.network.response.UserInfoResponse

interface UserDataSource {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    val downloadedUserInfo : LiveData<UserInfoResponse>
    suspend fun fetchUserInfo(queryParameterMap:  Map<String, String>)

    val downloadedApartmentList : LiveData<ApartmentListResponse>
    suspend fun fetchApartmentList(queryParameterMap:  Map<String, String>)



    // POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)
    val httpResponseMessage : HttpResponseMessage // Common for all POST

    suspend fun postNewApartment(postNewApartment: PostNewApartment)
    suspend fun postBuyer(postBuyer: PostBuyer)




    // PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)
    suspend fun updateProfile(queryParameterMap: Map<String, String>)
    suspend fun updateAddress(address: Address)




    // Upsert(Always POST)--------Upsert(Always POST)--------Upsert(Always POST)--------Upsert(Always POST)
    suspend fun upsertChefDetail(chefDetail: ChefDetail)



}