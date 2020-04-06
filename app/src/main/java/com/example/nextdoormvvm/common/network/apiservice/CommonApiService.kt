package com.example.nextdoormvvm.common.network.apiservice

import com.example.nextdoormvvm.common.network.post.PostDishViewed
import com.example.nextdoormvvm.common.network.response.OrderHistoryResponse
import com.example.nextdoormvvm.common.network.response.UserStatusResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface CommonApiService {


    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    @GET("Checkout/GetOrderHistoryByUserId")
    fun fetchOrderHistory(@QueryMap queryParameterMap: Map<String, String>): Deferred<OrderHistoryResponse>

    @GET("Registration/GetUserStatusById")
    fun fetchUserStatus(@QueryMap queryParameterMap: Map<String, String>): Deferred<UserStatusResponse>




    // POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)
    @POST("Analytic/PostDishViewed")
    fun postDishViewed(@Body postDishViewed: ArrayList<PostDishViewed>): Deferred<HttpResponseMessage>



    // PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)
    @PUT("CheckOut/AcceptOrderByOrderIds")
    fun acceptOrderByOrderIds(@QueryMap queryParameterMap: Map<String, String>): Deferred<HttpResponseMessage>

    @PUT("CheckOut/UpdateDeliveryStatusByOrderIds")
    fun updateDeliveryStatusByOrderIds(@QueryMap queryParameterMap: Map<String, String>): Deferred<HttpResponseMessage>

    @PUT("CheckOut/RejectRequestedOrderByOrderId")
    fun rejectRequestedOrderByOrderId(@QueryMap queryParameterMap: Map<String, String>): Deferred<HttpResponseMessage>


}