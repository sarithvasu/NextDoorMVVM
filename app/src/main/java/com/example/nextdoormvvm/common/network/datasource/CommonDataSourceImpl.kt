package com.example.nextdoormvvm.common.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nextdoormvvm.common.network.response.OrderHistoryResponse
import com.example.nextdoormvvm.common.network.apiservice.CommonApiService
import com.example.nextdoormvvm.common.network.post.PostDishViewed
import com.example.nextdoormvvm.common.network.response.UserStatusResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.internal.NoConnectivityException

class CommonDataSourceImpl(private val apiService: CommonApiService) : CommonDataSource {

    // ------------------------------------------------------Get(Select)------------------------------------------------
    // Get Order History from Server
    private val _downloadedOrderHistory = MutableLiveData<OrderHistoryResponse>()
    override val downloadedOrderHistory: LiveData<OrderHistoryResponse>
        get() = _downloadedOrderHistory

    override suspend fun fetchOrderHistory(queryParameterMap: Map<String, String>) {
        try {
            val fetchedOrderHistory = apiService.fetchOrderHistory(queryParameterMap).await()
            _downloadedOrderHistory.postValue(fetchedOrderHistory)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


    // Get Order History from Server
    private val _downloadedUserStatus = MutableLiveData<UserStatusResponse>()
    override val downloadedUserStatus: LiveData<UserStatusResponse>
        get() = _downloadedUserStatus

    override suspend fun fetchUserStatus(queryParameterMap: Map<String, String>) {
        try {
            val fetchedUserStatus = apiService.fetchUserStatus(queryParameterMap).await()
            _downloadedUserStatus.postValue(fetchedUserStatus)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }




    // ------------------------------------------------------POST(INSERT)------------------------------------------------
    private var _httpResponseMessage = HttpResponseMessage()

    override val httpResponseMessage: HttpResponseMessage
        get() = _httpResponseMessage

    override suspend fun postDishViewed(postDishViewed: ArrayList<PostDishViewed>) {
        try {
            val responseMessage = apiService.postDishViewed(postDishViewed).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


    // ------------------------------------------------------PUT(Update)------------------------------------------------

    override suspend fun acceptOrderByOrderIds(queryParameterMap: Map<String, String>) {
        try {
            val responseMessage = apiService.acceptOrderByOrderIds(queryParameterMap).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun updateDeliveryStatusByOrderIds(queryParameterMap: Map<String, String>) {
        try {
            val responseMessage = apiService.updateDeliveryStatusByOrderIds(queryParameterMap).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun rejectRequestedOrderByOrderId(queryParameterMap: Map<String, String>) {
        try {
            val responseMessage = apiService.rejectRequestedOrderByOrderId(queryParameterMap).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }
}