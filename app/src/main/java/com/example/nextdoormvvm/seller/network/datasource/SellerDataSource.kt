package com.example.nextdoormvvm.seller.network.datasource

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.seller.network.post.ActiveDish
import com.example.nextdoormvvm.seller.network.post.Dish
import com.example.nextdoormvvm.seller.network.response.*

interface SellerDataSource {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    val downloadedDishFeed : LiveData<DishFeedResponse>
    suspend fun fetchDishFeed(queryParameterMap: Map<String, String>)

    val downloadedChefSalesOrders : LiveData<ChefOrdersResponse>
    suspend fun fetchChefSalesOrders(queryParameterMap: Map<String, String>)

    val downloadedLedgerFeed : LiveData<LedgerFeedResponse>
    suspend fun fetchLedgerFeed(queryParameterMap: Map<String, String>)

    val downloadedDishViewedFeed : LiveData<ArrayList<DishViewedFeedResponse>>
    suspend fun fetchDishViewedFeed(queryParameterMap: Map<String, String>)

    val downloadedDishSharedFeed : LiveData<ArrayList<DishSharedFeedResponse>>
    suspend fun fetchDishSharedFeed(queryParameterMap: Map<String, String>)


    // POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)



// PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)
    val httpResponseMessage : HttpResponseMessage // Common for all POST

    suspend fun upsertDish(dish: Dish)
    suspend fun upsertActiveDish(activeDish: ActiveDish)

}