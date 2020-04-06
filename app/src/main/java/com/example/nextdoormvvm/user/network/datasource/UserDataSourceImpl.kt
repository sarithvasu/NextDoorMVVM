package com.example.nextdoormvvm.user.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.user.network.response.UserInfoResponse
import com.example.nextdoormvvm.internal.NoConnectivityException
import com.example.nextdoormvvm.user.network.apiservice.UserApiService
import com.example.nextdoormvvm.user.network.post.PostBuyer
import com.example.nextdoormvvm.user.network.post.PostNewApartment
import com.example.nextdoormvvm.user.network.upsert.ChefDetail
import com.example.nextdoormvvm.user.network.put.Address
import com.example.nextdoormvvm.user.network.response.ApartmentListResponse

class UserDataSourceImpl(private val userApiService: UserApiService) : UserDataSource {


    // ------------------------------------------------------Get(Select)------------------------------------------------
        // Get User info from server
    private val _downloadedUserInfo = MutableLiveData<UserInfoResponse>()
    override val downloadedUserInfo: LiveData<UserInfoResponse>
        get() = _downloadedUserInfo

    override suspend fun fetchUserInfo(queryParameterMap: Map<String, String>) {
        try {
            val fetchedUserInfo = userApiService.fetchUserInfo(queryParameterMap).await()
            _downloadedUserInfo.postValue(fetchedUserInfo)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


    // Get Apartment List from server
    private val _downloadedApartmentList = MutableLiveData<ApartmentListResponse>()
    override val downloadedApartmentList: LiveData<ApartmentListResponse>
        get() = _downloadedApartmentList

    override suspend fun fetchApartmentList(queryParameterMap: Map<String, String>) {
        try {
            val fetchedApartmentList = userApiService.fetchApartmentList(queryParameterMap).await()
            _downloadedApartmentList.postValue(fetchedApartmentList)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


    // ------------------------------------------------------POST(INSERT)------------------------------------------------
    private var _httpResponseMessage = HttpResponseMessage()
    override val httpResponseMessage: HttpResponseMessage
        get() = _httpResponseMessage

    override suspend fun postNewApartment(postNewApartment: PostNewApartment) {
        try {
            val responseMessage = userApiService.postNewApartment(postNewApartment).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun postBuyer(postBuyer: PostBuyer) {
        try {
            val responseMessage = userApiService.postBuyer(postBuyer).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }




    // ------------------------------------------------------PUT(Update)------------------------------------------------
    override suspend fun updateProfile(queryParameterMap: Map<String, String>) {
        try {
            val responseMessage = userApiService.updateProfile(queryParameterMap).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun updateAddress(address: Address) {
        try {
            val responseMessage = userApiService.updateAddress(address).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }




    // ------------------------------------------------------Upsert(Always POST)------------------------------------------------
    override suspend fun upsertChefDetail(chefDetail: ChefDetail) {
        try {
            val responseMessage = userApiService.upsertChefDetail(chefDetail).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


}