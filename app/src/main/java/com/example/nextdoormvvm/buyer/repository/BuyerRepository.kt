package com.example.nextdoormvvm.buyer.repository

import androidx.lifecycle.LiveData
import com.example.nextdoormvvm.buyer.network.post.*
import com.example.nextdoormvvm.buyer.network.response.CartItem
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.user.network.response.UserInfoResponse

interface BuyerRepository {



    // Only Local DB call
    suspend fun fetchOnlineOrderFromDB(buyerId: Int): OnlineOrder
    suspend fun fetchOnlineOrderFromDBLiveData(buyerId: Int): LiveData<out OnlineOrder>
    suspend fun persistOnlineOrder(onlineOrder: OnlineOrder)
    suspend fun deleteOnlineOrderFromDB()


    // Network------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    suspend fun fetchHomeFeed(queryParameterMap: Map<String, String>): LiveData<out HomeFeedResponse>
    suspend fun fetchStock(cartItems: ArrayList<CartItem>): LiveData<ArrayList<StockResponse>>


    // Network POST(INSERT)-------POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)-----POST(INSERT)
    suspend fun postSharedDishDetail(postSharedDishDetail: PostSharedDishDetail): HttpResponseMessage
    suspend fun postRatingAndReviewPopup(postRatingAndReviewPopup: ArrayList<PostRatingAndReviewPopup>): HttpResponseMessage
    suspend fun postChefFollower(chefFollower: ChefFollower): HttpResponseMessage
    suspend fun postTestimonial(testimonial: Testimonial): HttpResponseMessage
    suspend fun postPurchaseOrderByUPI(postcheckout: PostPurchaseOrder): HttpResponseMessage
    suspend fun postPurchaseOrderByCOD(postcheckout: PostPurchaseOrder): HttpResponseMessage
    suspend fun postOrderByRequest(order: Order): HttpResponseMessage


    //Network   PUT(Update)-------PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)-----PUT(Update)
    suspend fun updateChefFollower(chefFollower: ChefFollower): HttpResponseMessage

    suspend fun buyerInfoFromDB(): UserInfoResponse

}