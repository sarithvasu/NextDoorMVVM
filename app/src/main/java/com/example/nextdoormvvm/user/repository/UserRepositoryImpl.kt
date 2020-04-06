package com.example.nextdoormvvm.user.repository

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.user.network.response.UserInfoResponse
import com.example.nextdoormvvm.db.NextDoorDatabase
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.internal.NextDoorApplication
import com.example.nextdoormvvm.user.network.datasource.UserDataSource
import com.example.nextdoormvvm.user.network.post.PostBuyer
import com.example.nextdoormvvm.user.network.post.PostNewApartment
import com.example.nextdoormvvm.user.network.upsert.ChefDetail
import com.example.nextdoormvvm.user.network.put.Address
import com.example.nextdoormvvm.user.network.response.ApartmentListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepositoryImpl (private val userDataSource: UserDataSource) : UserRepository {

    init {
        userDataSource.downloadedUserInfo.observeForever{ newUserInfo ->
            persistUserInfo(newUserInfo)
        }
    }

    override suspend fun fetchApartmentList(queryParameterMap: Map<String, String>): LiveData<out ApartmentListResponse> {
        return withContext(Dispatchers.IO) {
            userDataSource.fetchApartmentList(queryParameterMap)
            return@withContext userDataSource.downloadedApartmentList
        }
    }


    override suspend fun fetchUserInfo(queryParameterMap: Map<String, String>): LiveData<out UserInfoResponse> {
        return withContext(Dispatchers.IO) {
            userDataSource.fetchUserInfo(queryParameterMap)
            return@withContext NextDoorDatabase.invoke(NextDoorApplication.context!!).userDao().getUserInfo()
        }
    }
    override suspend fun fetchUserInfoFromDB(queryParameterMap: Map<String, String>): UserInfoResponse {
        return withContext(Dispatchers.IO) {
            return@withContext NextDoorDatabase.invoke(NextDoorApplication.context!!).userDao().fetchUserInfoFromDB()
        }
    }
    private fun persistUserInfo(fetchedUserInfo: UserInfoResponse) {
        // Launch doesn't return a valueif
        GlobalScope.launch(Dispatchers.IO) {
            NextDoorDatabase.invoke(NextDoorApplication.context!!).userDao().upsert(fetchedUserInfo)
        }
    }


    override suspend fun updateInfoAtDB(columnName: String, columnValue: String) {
        GlobalScope.launch(Dispatchers.IO) {
            NextDoorDatabase.invoke(NextDoorApplication.context!!).userDao().updateInfoAtDB(columnName,columnValue)
        }
    }


    // POST-------POST-----POST-----POST-----POST
    override suspend fun postNewApartment(postNewApartment: PostNewApartment): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            userDataSource.postNewApartment(postNewApartment)
            return@withContext userDataSource.httpResponseMessage
        }
    }

    override suspend fun postBuyer(postBuyer: PostBuyer): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            userDataSource.postBuyer(postBuyer)
            return@withContext userDataSource.httpResponseMessage
        }
    }

    override suspend fun upsertChefDetail(chefDetail: ChefDetail): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            userDataSource.upsertChefDetail(chefDetail)
            return@withContext userDataSource.httpResponseMessage
        }
    }

    override suspend fun updateProfile(queryParameterMap: Map<String, String>): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            userDataSource.updateProfile(queryParameterMap)
            return@withContext userDataSource.httpResponseMessage
        }
    }

    override suspend fun updateAddress(address: Address): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            userDataSource.updateAddress(address)
            return@withContext userDataSource.httpResponseMessage
        }
    }

}