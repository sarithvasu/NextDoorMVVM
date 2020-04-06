package com.example.nextdoormvvm.seller.network.apiservice

import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.seller.network.post.ActiveDish
import com.example.nextdoormvvm.seller.network.post.Dish
import com.example.nextdoormvvm.seller.network.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface SellerApiService {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    @GET("Feed/GetDishFeedByChefId")
    fun fetchDishFeed(@QueryMap queryParameterMap: Map<String, String>): Deferred<DishFeedResponse>

    @GET("CheckOut/GetChefSalesOrdersById")
    fun fetchChefSalesOrders(@QueryMap  queryParameterMap: Map<String, String>): Deferred<ChefOrdersResponse>

    @GET("Accounting/GetLedgerFeedByChefId")
    fun fetchLedgerFeed(@QueryMap  queryParameterMap: Map<String, String>): Deferred<LedgerFeedResponse>


    @GET("Analytic/GetDishViewedFeedByDishId")
    fun fetchDishViewedFeed(@QueryMap  queryParameterMap: Map<String, String>): Deferred<ArrayList<DishViewedFeedResponse>>

    @GET("Analytic/GetSharedFeedByDishId")
    fun fetchDishSharedFeed(@QueryMap  queryParameterMap: Map<String, String>): Deferred<ArrayList<DishSharedFeedResponse>>



    // POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)

    @POST(value = "Product/ManageDish")
    fun upsertDish(@Body dish: Dish): Deferred<HttpResponseMessage>

    @POST(value = "Product/ManegeActivateDish")
    fun upsertActiveDish(@Body activeDish: ActiveDish): Deferred<HttpResponseMessage>




    // PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)

}