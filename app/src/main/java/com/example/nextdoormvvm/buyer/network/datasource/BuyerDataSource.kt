package com.example.nextdoormvvm.buyer.network.datasource

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.buyer.network.post.*
import com.example.nextdoormvvm.buyer.network.response.CartItem
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage

interface BuyerDataSource {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    val downloadedHomeFeed : LiveData<HomeFeedResponse>
    suspend fun fetchHomeFeed(queryParameterMap: Map<String, String>)

    val downloadedStock : LiveData<ArrayList<StockResponse>>
    suspend fun fetchStock(cartItems: ArrayList<CartItem>)



    // POST-------POST-----POST-----POST-----POST----POST-------POST-----POST-----POST-----POST
    val httpResponseMessage : HttpResponseMessage // Common for all POST. This variable to be Declared only once throughout this interface


    suspend fun postSharedDishDetail (postSharedDishDetail: PostSharedDishDetail)
    suspend fun postRatingAndReviewPopup (postRatingAndReviewPopup: ArrayList<PostRatingAndReviewPopup>)
    suspend fun postChefFollower (chefFollower: ChefFollower)
    suspend fun postTestimonial (testimonial: Testimonial)
    suspend fun postPurchaseOrderByUPI(postPurchaseOrder: PostPurchaseOrder)
    suspend fun postPurchaseOrderByCOD(postPurchaseOrder: PostPurchaseOrder)
    suspend fun postOrderByRequest(order: Order)



    // UPADTE-------UPADTE-----UPADTE-----UPADTE-----UPADTE-------UPADTE-----UPADTE-----UPADTE
    suspend fun updateChefFollower (chefFollower: ChefFollower)

}
