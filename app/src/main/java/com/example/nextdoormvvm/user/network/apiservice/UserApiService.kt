package com.example.nextdoormvvm.user.network.apiservice

import com.example.nextdoormvvm.user.network.post.PostNewApartment
import com.example.nextdoormvvm.user.network.response.ApartmentListResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.user.network.post.PostBuyer
import com.example.nextdoormvvm.user.network.upsert.ChefDetail
import com.example.nextdoormvvm.user.network.put.Address
import com.example.nextdoormvvm.user.network.response.UserInfoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface UserApiService {


    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    @GET("Registration/GetUserInfoByMobileNumber")
    fun fetchUserInfo(@QueryMap queryParameterMap: Map<String, String>): Deferred<UserInfoResponse>

    @GET("Registration/GetApartmentList")
    fun fetchApartmentList(@QueryMap queryParameterMap: Map<String, String>): Deferred<ApartmentListResponse>




    // POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)
    @POST("Registration/PostNewApartmentRequest")
    fun postNewApartment(@Body postNewApartment: PostNewApartment): Deferred<HttpResponseMessage>

    @POST("Registration/PostBuyer")
    fun postBuyer (@Body postBuyer: PostBuyer): Deferred<HttpResponseMessage>



    // PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)
    @PUT("Registration/UpdateProfile")
    fun updateProfile( @QueryMap  queryParameterMap: Map<String, String>): Deferred<HttpResponseMessage>

    @PUT("Registration/ChangeAddress")
    fun updateAddress(@Body address: Address): Deferred<HttpResponseMessage>



    // Upsert(Always POST)--------Upsert(Always POST)--------Upsert(Always POST)--------Upsert(Always POST)
    @POST("Registration/UpsertChefDetail")
    fun upsertChefDetail (@Body chefDetail: ChefDetail): Deferred<HttpResponseMessage>

}