package com.example.nextdoormvvm.seller.repository

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.seller.network.post.ActiveDish
import com.example.nextdoormvvm.seller.network.post.Dish
import com.example.nextdoormvvm.seller.network.response.*

interface SellerRepository {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    suspend fun fetchDishFeed(queryParameterMap: Map<String, String>): LiveData<out DishFeedResponse>
    suspend fun fetchChefSalesOrders(queryParameterMap: Map<String, String>): LiveData<out ChefOrdersResponse>
    suspend fun fetchLedgerFeed(queryParameterMap: Map<String, String>): LiveData<out LedgerFeedResponse>
    suspend fun fetchDishViewedFeed(queryParameterMap: Map<String, String>): LiveData<out ArrayList<DishViewedFeedResponse>>
    suspend fun fetchDishSharedFeed(queryParameterMap: Map<String, String>): LiveData<out ArrayList<DishSharedFeedResponse>>


    // POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)
    suspend fun upsertDish(dish: Dish): HttpResponseMessage
    suspend fun upsertActiveDish(activeDish: ActiveDish): HttpResponseMessage


    // PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)

}