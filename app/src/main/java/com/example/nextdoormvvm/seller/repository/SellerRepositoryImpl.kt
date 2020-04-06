package com.example.nextdoormvvm.seller.repository

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.seller.network.datasource.SellerDataSource
import com.example.nextdoormvvm.seller.network.post.ActiveDish
import com.example.nextdoormvvm.seller.network.post.Dish
import com.example.nextdoormvvm.seller.network.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SellerRepositoryImpl(private val sellerDataSource: SellerDataSource) : SellerRepository {

    // ------------------------------------------------------Get(Select)------------------------------------------------
    // get Chef Feed from server
    override suspend fun fetchDishFeed(queryParameterMap: Map<String, String>): LiveData<out DishFeedResponse> {

        return withContext(Dispatchers.IO) {
            sellerDataSource.fetchDishFeed(queryParameterMap)
            return@withContext sellerDataSource.downloadedDishFeed
        }
    }

    // get Chef Orders from server
    override suspend fun fetchChefSalesOrders(queryParameterMap: Map<String, String>): LiveData<out ChefOrdersResponse> {
        return withContext(Dispatchers.IO) {
            sellerDataSource.fetchChefSalesOrders(queryParameterMap)
            return@withContext sellerDataSource.downloadedChefSalesOrders
        }
    }

    // LedgerFeed data from server
    override suspend fun fetchLedgerFeed(queryParameterMap: Map<String, String>): LiveData<out LedgerFeedResponse> {
        return withContext(Dispatchers.IO) {
            sellerDataSource.fetchLedgerFeed(queryParameterMap)
            return@withContext sellerDataSource.downloadedLedgerFeed
        }
    }

    override suspend fun fetchDishViewedFeed(queryParameterMap: Map<String, String>): LiveData<out ArrayList<DishViewedFeedResponse>> {
        return withContext(Dispatchers.IO) {
            sellerDataSource.fetchDishViewedFeed(queryParameterMap)
            return@withContext sellerDataSource.downloadedDishViewedFeed
        }
    }

    override suspend fun fetchDishSharedFeed(queryParameterMap: Map<String, String>): LiveData<out ArrayList<DishSharedFeedResponse>> {
        return withContext(Dispatchers.IO) {
            sellerDataSource.fetchDishSharedFeed(queryParameterMap)
            return@withContext sellerDataSource.downloadedDishSharedFeed
        }
    }



    // ------------------------------------------------------POST(INSERT)------------------------------------------------
    override suspend fun upsertDish(dish: Dish): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            sellerDataSource.upsertDish(dish)
            return@withContext sellerDataSource.httpResponseMessage
        }
    }

    override suspend fun upsertActiveDish(activeDish: ActiveDish): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            sellerDataSource.upsertActiveDish(activeDish)
            return@withContext sellerDataSource.httpResponseMessage
        }
    }

    // ------------------------------------------------------PUT(Update)------------------------------------------------



}