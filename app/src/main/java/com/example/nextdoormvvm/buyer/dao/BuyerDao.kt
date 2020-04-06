package com.example.nextdoormvvm.buyer.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nextdoormvvm.buyer.network.post.OnlineOrder
import com.example.nextdoormvvm.buyer.network.post.PurchaseOrder
import com.example.nextdoormvvm.buyer.network.post.UpiPaymentDetail
import com.example.nextdoormvvm.user.network.response.CURRENT_USER_ID
import com.example.nextdoormvvm.user.network.response.UserInfoResponse

@Dao
interface BuyerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPurchaseOrders(purchaseOrders: List<PurchaseOrder>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpiPaymentDetail(upiPaymentDetail: UpiPaymentDetail?)


    @Query("select * from purchase_order where BuyerId = :buyerId")
    fun getPurchaseOrders(buyerId: Int): List<PurchaseOrder>

    @Query("select * from upi_payment_detail where BuyerId = :buyerId")
    fun getUpiPaymentDetail(buyerId: Int): UpiPaymentDetail



    @Query("delete from purchase_order")
    fun deletePurchaseOrders()

    @Query("delete from upi_payment_detail")
    fun deleteUpiPaymentDetail()

}