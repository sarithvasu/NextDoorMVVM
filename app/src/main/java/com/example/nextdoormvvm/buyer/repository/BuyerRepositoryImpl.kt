package com.example.nextdoormvvm.buyer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nextdoormvvm.buyer.network.datasource.BuyerDataSource
import com.example.nextdoormvvm.buyer.network.post.*
import com.example.nextdoormvvm.buyer.network.response.CartItem
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.db.NextDoorDatabase
import com.example.nextdoormvvm.internal.HttpResponseMessage
import com.example.nextdoormvvm.internal.NextDoorApplication
import com.example.nextdoormvvm.user.network.response.UserInfoResponse

import kotlinx.coroutines.*

class BuyerRepositoryImpl(private val buyerDataSource: BuyerDataSource) : BuyerRepository {

    // ------------------------------------------------------Get(Select)------------------------------------------------
    override suspend fun fetchHomeFeed(queryParameterMap: Map<String, String>): LiveData<out HomeFeedResponse> {

        return withContext(Dispatchers.IO) {
            buyerDataSource.fetchHomeFeed(queryParameterMap)
            return@withContext buyerDataSource.downloadedHomeFeed
        }
    }

    override suspend fun fetchStock(cartItems: ArrayList<CartItem>): LiveData<ArrayList<StockResponse>> {
        return withContext(Dispatchers.IO) {
            buyerDataSource.fetchStock(cartItems)
            return@withContext buyerDataSource.downloadedStock
        }
    }

    override suspend fun fetchOnlineOrderFromDB(buyerId: Int): OnlineOrder {
        return withContext(Dispatchers.IO) {
            val po= NextDoorDatabase.invoke(NextDoorApplication.context!!).buyerDao().getPurchaseOrders(buyerId)
            val payment = NextDoorDatabase.invoke(NextDoorApplication.context!!).buyerDao().getUpiPaymentDetail(buyerId)
            val onlineOrder = OnlineOrder(po,payment)
            return@withContext onlineOrder
        }
    }

    override suspend fun fetchOnlineOrderFromDBLiveData(buyerId: Int): LiveData<out OnlineOrder> {
        return withContext(Dispatchers.IO) {
            val po= NextDoorDatabase.invoke(NextDoorApplication.context!!).buyerDao().getPurchaseOrders(buyerId)
            val payment = NextDoorDatabase.invoke(NextDoorApplication.context!!).buyerDao().getUpiPaymentDetail(buyerId)
            val onlineOrder = OnlineOrder(po,payment)
            val  mutablelOnlineOrder = MutableLiveData<OnlineOrder>()
            mutablelOnlineOrder.postValue(onlineOrder)
            return@withContext mutablelOnlineOrder
        }
    }

    override suspend fun persistOnlineOrder(onlineOrder: OnlineOrder) {
        GlobalScope.launch(Dispatchers.IO) {
            NextDoorDatabase.invoke(NextDoorApplication.context!!).buyerDao().insertPurchaseOrders(onlineOrder.purchaseOrders)
            NextDoorDatabase.invoke(NextDoorApplication.context!!).buyerDao().insertUpiPaymentDetail(onlineOrder.upiPaymentDetail)
        }
    }

    override suspend fun deleteOnlineOrderFromDB(){
        GlobalScope.launch(Dispatchers.IO) {
            NextDoorDatabase.invoke(NextDoorApplication.context!!).buyerDao().deletePurchaseOrders()
            NextDoorDatabase.invoke(NextDoorApplication.context!!).buyerDao().deleteUpiPaymentDetail()
        }
    }


    // ------------------------------------------------------POST(INSERT)------------------------------------------------
    override suspend fun postSharedDishDetail(postSharedDishDetail: PostSharedDishDetail): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            buyerDataSource.postSharedDishDetail(postSharedDishDetail)
            return@withContext buyerDataSource.httpResponseMessage
        }
    }

    override suspend fun postRatingAndReviewPopup(postRatingAndReviewPopup: ArrayList<PostRatingAndReviewPopup>): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            buyerDataSource.postRatingAndReviewPopup (postRatingAndReviewPopup)
            return@withContext buyerDataSource.httpResponseMessage
        }
    }

    override suspend fun postChefFollower(chefFollower: ChefFollower): HttpResponseMessage {

            return withContext(Dispatchers.IO) {
                buyerDataSource.postChefFollower(chefFollower)
                return@withContext buyerDataSource.httpResponseMessage
            }
    }


    override suspend fun postTestimonial(testimonial: Testimonial): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            buyerDataSource.postTestimonial (testimonial)
            return@withContext buyerDataSource.httpResponseMessage
        }
    }

    override suspend fun updateChefFollower(chefFollower: ChefFollower): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            buyerDataSource.updateChefFollower(chefFollower)
            return@withContext buyerDataSource.httpResponseMessage
        }
    }

    override suspend fun buyerInfoFromDB(): UserInfoResponse {
        return withContext(Dispatchers.IO) {
            return@withContext NextDoorDatabase.invoke(NextDoorApplication.context!!).userDao().fetchUserInfoFromDB()
        }
    }


    override suspend fun postPurchaseOrderByUPI(postcheckout: PostPurchaseOrder): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            buyerDataSource.postPurchaseOrderByUPI(postcheckout)
            return@withContext buyerDataSource.httpResponseMessage
        }
    }

    override suspend fun postPurchaseOrderByCOD(postcheckout: PostPurchaseOrder): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            buyerDataSource.postPurchaseOrderByCOD(postcheckout)
            return@withContext buyerDataSource.httpResponseMessage
        }
    }

    override suspend fun postOrderByRequest(order: Order): HttpResponseMessage {
        return withContext(Dispatchers.IO) {
            buyerDataSource.postOrderByRequest(order)
            return@withContext buyerDataSource.httpResponseMessage
        }
    }


    // ------------------------------------------------------PUT(Update)------------------------------------------------


}