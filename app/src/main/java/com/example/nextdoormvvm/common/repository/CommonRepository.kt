package com.example.nextdoormvvm.common.repository

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.common.network.post.PostDishViewed
import com.example.nextdoormvvm.common.network.response.OrderHistoryResponse
import com.example.nextdoormvvm.common.network.response.UserStatusResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage

interface CommonRepository {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    suspend fun fetchOrderHistory(queryParameterMap: Map<String, String>): LiveData<out OrderHistoryResponse>
    suspend fun fetchUserStatus(queryParameterMap: Map<String, String>): LiveData<out UserStatusResponse>


    // POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)
    suspend fun postDishViewed(postDishViewed: ArrayList<PostDishViewed>): HttpResponseMessage



    // PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)
    suspend fun acceptOrderByOrderIds(queryParameterMap: Map<String, String>): HttpResponseMessage
    suspend fun updateDeliveryStatusByOrderIds(queryParameterMap: Map<String, String>): HttpResponseMessage
    suspend fun rejectRequestedOrderByOrderId(queryParameterMap: Map<String, String>): HttpResponseMessage


}