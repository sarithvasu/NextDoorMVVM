package com.example.nextdoormvvm.common.network.datasource

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.common.network.post.PostDishViewed
import com.example.nextdoormvvm.common.network.response.OrderHistoryResponse
import com.example.nextdoormvvm.common.network.response.UserStatusResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage

interface CommonDataSource {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    val downloadedOrderHistory : LiveData<OrderHistoryResponse>
    suspend fun fetchOrderHistory(queryParameterMap:  Map<String, String>)

    val downloadedUserStatus : LiveData<UserStatusResponse>
    suspend fun fetchUserStatus(queryParameterMap:  Map<String, String>)



    // POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)
    val httpResponseMessage : HttpResponseMessage // Common for all POST
    suspend fun postDishViewed (postDishViewed: ArrayList<PostDishViewed>)




    // PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)
    suspend fun acceptOrderByOrderIds(queryParameterMap:  Map<String, String>)
    suspend fun updateDeliveryStatusByOrderIds(queryParameterMap:  Map<String, String>)
    suspend fun rejectRequestedOrderByOrderId(queryParameterMap:  Map<String, String>)
}
