package com.example.nextdoormvvm.seller.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.internal.NoConnectivityException
import com.example.nextdoormvvm.seller.network.apiservice.SellerApiService
import com.example.nextdoormvvm.seller.network.post.ActiveDish
import com.example.nextdoormvvm.seller.network.post.Dish
import com.example.nextdoormvvm.seller.network.response.*


class SellerDataSourceImpl(private val sellerApiService: SellerApiService) : SellerDataSource {

    // ------------------------------------------------------Get(Select)------------------------------------------------
    // Chef Home Feed
    private val _downloadedDishFeed = MutableLiveData<DishFeedResponse>()
    override val downloadedDishFeed: LiveData<DishFeedResponse>
        get() = _downloadedDishFeed

    override suspend fun fetchDishFeed(queryParameterMap: Map<String, String>) {
        try {
            val fetchedDishFeed = sellerApiService.fetchDishFeed(queryParameterMap).await()
            _downloadedDishFeed.postValue(fetchedDishFeed)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


    // Chef Orders
    private val _downloadedChefSalesOrders = MutableLiveData<ChefOrdersResponse>()
    override val downloadedChefSalesOrders: LiveData<ChefOrdersResponse>
        get() = _downloadedChefSalesOrders

    override suspend fun fetchChefSalesOrders(queryParameterMap: Map<String, String>) {
        try {
            val fetchedChefOrderes = sellerApiService.fetchChefSalesOrders(queryParameterMap).await()
            _downloadedChefSalesOrders.postValue(fetchedChefOrderes)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }



    // LedgerFeed data
    private val _downloadedLedgerFeed = MutableLiveData<LedgerFeedResponse>()
    override val downloadedLedgerFeed: LiveData<LedgerFeedResponse>
        get() = _downloadedLedgerFeed

    override suspend fun fetchLedgerFeed(queryParameterMap: Map<String, String>) {
        try {
            val fetchedLedgerFeed = sellerApiService.fetchLedgerFeed(queryParameterMap).await()
            _downloadedLedgerFeed.postValue(fetchedLedgerFeed)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    // Dish Viewed Feed
    private val _downloadedDishViewedFeed = MutableLiveData<ArrayList<DishViewedFeedResponse>>()
    override val downloadedDishViewedFeed: LiveData<ArrayList<DishViewedFeedResponse>>
        get() = _downloadedDishViewedFeed

    override suspend fun fetchDishViewedFeed(queryParameterMap: Map<String, String>) {
        try {
            val fetchedDishViewedFeed = sellerApiService.fetchDishViewedFeed(queryParameterMap).await()
            _downloadedDishViewedFeed.postValue(fetchedDishViewedFeed)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }



    // Shared Feed
    private val _downloadedDishSharedFeed = MutableLiveData<ArrayList<DishSharedFeedResponse>>()
    override val downloadedDishSharedFeed: LiveData<ArrayList<DishSharedFeedResponse>>
        get() = _downloadedDishSharedFeed

    override suspend fun fetchDishSharedFeed(queryParameterMap: Map<String, String>) {
        try {
            val fetchedSharedFeed = sellerApiService.fetchDishSharedFeed(queryParameterMap).await()
            _downloadedDishSharedFeed.postValue(fetchedSharedFeed)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


    // ------------------------------------------------------POST(INSERT)------------------------------------------------
    private var _httpResponseMessage = HttpResponseMessage()
    override val httpResponseMessage: HttpResponseMessage
        get() = _httpResponseMessage



    override suspend fun upsertDish(dish: Dish) {
        try {
            val responseMessage = sellerApiService.upsertDish(dish).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun upsertActiveDish(activeDish: ActiveDish) {
        try {
            val responseMessage = sellerApiService.upsertActiveDish(activeDish).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }



    // ------------------------------------------------------PUT(Update)------------------------------------------------


}