package com.example.nextdoormvvm.buyer.network.apiservice


import com.example.nextdoormvvm.buyer.network.post.*
import com.example.nextdoormvvm.buyer.network.response.CartItem
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.common.network.response.OrderHistoryResponse
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.user.network.response.UserInfoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface BuyerApiService {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)

    @GET("Feed/GetHomeFeedByApartmentId")
    fun fetchHomeFeed(@QueryMap  queryParameterMap: Map<String, String>): Deferred<HomeFeedResponse>


    @GET("Checkout/GetOrderHistoryByUserId")
    fun fetchOrderHistory(@QueryMap  queryParameterMap: Map<String, String>): Deferred<OrderHistoryResponse>


    @GET("Registration/GetUserInfoByMobileNumber")
    fun fetchUserInfo(@QueryMap  queryParameterMap: Map<String, String>): Deferred<UserInfoResponse>


    @POST(value = "Checkout/GetStock")
    fun fetchStock(@Body cartItems: ArrayList<CartItem>):Deferred<ArrayList<StockResponse>>



    // POST-------POST-----POST-----POST-----POST---POST-------POST-----POST-----POST-----POST
    @POST("Analytic/PostSharedDishDetail")
    fun postSharedDishDetail (@Body postSharedDishDetail: PostSharedDishDetail): Deferred<HttpResponseMessage>


    @POST("Analytic/PostRatingAndReview")
    fun postRatingAndReviewPopup(@Body postRatingAndReviewPopup: ArrayList<PostRatingAndReviewPopup>): Deferred<HttpResponseMessage>


    @POST("Analytic/PostChefFollower")
    fun postChefFollower (@Body chefFollower: ChefFollower): Deferred<HttpResponseMessage>


    @POST("Analytic/PostTestimonial")
    fun postTestimonial (@Body testimonial: Testimonial): Deferred<HttpResponseMessage>




    // UPADTE-------UPADTE-----UPADTE-----UPADTE-----UPADTE-------UPADTE-----UPADTE-----UPADTE
    @PUT("Analytic/UpdateChefFollower")
    fun updateChefFollower (@Body chefFollower: ChefFollower): Deferred<HttpResponseMessage>


    @POST(value = "Checkout/PostPurchaseOrderByUPI")
    fun postPurchaseOrderByUPI(@Body postPurchaseOrder: PostPurchaseOrder) : Deferred<HttpResponseMessage>

    @POST(value = "Checkout/PostPurchaseOrderByCOD")
    fun postPurchaseOrderByCOD(@Body postPurchaseOrder: PostPurchaseOrder) : Deferred<HttpResponseMessage>

    @POST(value = "Checkout/PostOrderByRequest")
    fun postOrderByRequest(@Body order: Order):Deferred<HttpResponseMessage>



}






