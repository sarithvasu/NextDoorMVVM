package com.example.nextdoormvvm.buyer.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nextdoormvvm.internal.NoConnectivityException
import com.example.nextdoormvvm.buyer.network.apiservice.BuyerApiService
import com.example.nextdoormvvm.buyer.network.post.*
import com.example.nextdoormvvm.buyer.network.response.CartItem
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.internal.NextDoorApplication
import com.google.gson.GsonBuilder

class BuyerDataSourceImpl(private val buyerApiService: BuyerApiService) : BuyerDataSource {

    // ------------------------------------------------------Get(Select)------------------------------------------------
    // Get Home Feed from Server
    private val _downloadedHomeFeed = MutableLiveData<HomeFeedResponse>()
    override val downloadedHomeFeed: LiveData<HomeFeedResponse>
        get() = _downloadedHomeFeed

    override suspend fun fetchHomeFeed(queryParameterMap: Map<String, String>) {
        try {
            val jsonstring = NextDoorApplication.context?.assets?.open("HomeFeed.json")?.bufferedReader().use { it?.readText() }
            val gson = GsonBuilder().serializeNulls().create()
            val fetchedHomeFeed = gson.fromJson(jsonstring, HomeFeedResponse::class.java)
            //val fetchedHomeFeed = buyerApiService.fetchHomeFeed(queryParameterMap).await()
            _downloadedHomeFeed.postValue(fetchedHomeFeed)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    private val _downloadedstock = MutableLiveData<ArrayList<StockResponse>>()
    override val downloadedStock: LiveData<ArrayList<StockResponse>>
        get() = _downloadedstock

    override suspend fun fetchStock(cartItems: ArrayList<CartItem>) {
        try {
            val fetchedStock = buyerApiService.fetchStock(cartItems).await()
            _downloadedstock.postValue(fetchedStock)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }






    // ------------------------------------------------------POST(INSERT)------------------------------------------------
    private var _httpResponseMessage = HttpResponseMessage()
    override val httpResponseMessage: HttpResponseMessage
        get() = _httpResponseMessage




    override suspend fun postSharedDishDetail(postSharedDishDetail: PostSharedDishDetail) {
        try {
            val responseMessage = buyerApiService.postSharedDishDetail(postSharedDishDetail).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


    override suspend fun postRatingAndReviewPopup(postRatingAndReviewPopup: ArrayList<PostRatingAndReviewPopup>) {
        try {
            val responseMessage = buyerApiService.postRatingAndReviewPopup(postRatingAndReviewPopup).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun postChefFollower(chefFollower: ChefFollower) {
        try {
            val responseMessage = buyerApiService.postChefFollower(chefFollower).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun postTestimonial(testimonial: Testimonial) {
        try {
            val responseMessage = buyerApiService.postTestimonial(testimonial).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun updateChefFollower(chefFollower: ChefFollower) {
        try {
            val responseMessage = buyerApiService.updateChefFollower(chefFollower).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


    override suspend fun postPurchaseOrderByUPI(postPurchaseOrder: PostPurchaseOrder) {
        try {
            val responseMessage = buyerApiService.postPurchaseOrderByUPI(postPurchaseOrder).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun postPurchaseOrderByCOD(postPurchaseOrder: PostPurchaseOrder) {
        try {
            val responseMessage = buyerApiService.postPurchaseOrderByCOD(postPurchaseOrder).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }

    override suspend fun postOrderByRequest(order: Order) {
        try {
            val responseMessage = buyerApiService.postOrderByRequest(order).await()
            _httpResponseMessage = responseMessage

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


}