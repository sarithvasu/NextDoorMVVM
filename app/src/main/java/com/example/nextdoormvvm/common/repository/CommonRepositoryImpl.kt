package com.example.nextdoormvvm.common.repository

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.common.network.response.OrderHistoryResponse
import com.example.nextdoormvvm.common.network.datasource.CommonDataSource
import com.example.nextdoormvvm.common.network.post.PostDishViewed
import com.example.nextdoormvvm.common.network.response.UserStatusResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommonRepositoryImpl(private val commonDataSource: CommonDataSource)  : CommonRepository {

    // ------------------------------------------------------Get(Select)------------------------------------------------
    override suspend fun fetchOrderHistory(queryParameterMap: Map<String, String>): LiveData<out OrderHistoryResponse> {
        return withContext(Dispatchers.IO) {
            commonDataSource.fetchOrderHistory(queryParameterMap)
            return@withContext commonDataSource.downloadedOrderHistory
        }
    }


    override suspend fun fetchUserStatus(queryParameterMap: Map<String, String>): LiveData<out UserStatusResponse> {
        return withContext(Dispatchers.IO) {
            commonDataSource.fetchUserStatus(queryParameterMap)
            return@withContext commonDataSource.downloadedUserStatus
        }
    }



    // ------------------------------------------------------POST(INSERT)------------------------------------------------
    override suspend fun postDishViewed(postDishViewed: ArrayList<PostDishViewed>): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            commonDataSource.postDishViewed(postDishViewed)
            return@withContext commonDataSource.httpResponseMessage
        }
    }



    // ------------------------------------------------------PUT(Update)------------------------------------------------
    override suspend fun acceptOrderByOrderIds(queryParameterMap: Map<String, String>): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            commonDataSource.acceptOrderByOrderIds(queryParameterMap)
            return@withContext commonDataSource.httpResponseMessage
        }
    }

    override suspend fun updateDeliveryStatusByOrderIds(queryParameterMap: Map<String, String>): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            commonDataSource.updateDeliveryStatusByOrderIds(queryParameterMap)
            return@withContext commonDataSource.httpResponseMessage
        }
    }

    override suspend fun rejectRequestedOrderByOrderId(queryParameterMap: Map<String, String>): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            commonDataSource.rejectRequestedOrderByOrderId(queryParameterMap)
            return@withContext commonDataSource.httpResponseMessage
        }
    }

}