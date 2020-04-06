package com.example.nextdoormvvm.buyer.network.post

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nextdoormvvm.internal.BusinessObject



data class OnlineOrder(
    val purchaseOrders: List<PurchaseOrder>?,
    val upiPaymentDetail: UpiPaymentDetail?
): BusinessObject()


@Entity(tableName = "upi_payment_detail")
data class UpiPaymentDetail(
    val BuyerId: Int=-1,
    val SellerId:  Int=-1,
    val ChefId:  Int=-1,
    var ApprovalReferenceNumberBeneficiary: String="",
    var CurrencyCode:  String="",
    var ResponseCode:  String="",
    var TransactionAmount: Int=-1,
    var TransactionId:  String="",
    var TransactionNote:  String="",
    val TransactionReferenceId:  String="",
    var TransactionStatus:  String=""
){
    @PrimaryKey(autoGenerate = true) var room_payment_id: Int = 0
}

@Entity(tableName = "purchase_order")
data class PurchaseOrder(
    val ChefId: Int,
    val SellerUserId: Int,
    val DeliveryEndTime: String?,
    val DeliveryStartTime: String?,
    val DeliveryCharge: Int,
    val DeliveryOptions: Int?,
    val Discount: Int,
    val DishId: Int,
    val ItemTotal: Int,
    val OrderTotal: Int,
    val PackingCharge: Int,
    val PackingOptions: Int?,
    val PaymentMode: String,
    val OrderQuantity: Int,
    val TotalDeliveryCharge: Int,
    val TotalPackingCharge: Int,
    val BuyerId: Int,
    val ServiceCharge: Int,
    val RequestStatus: String?,
    val RejectNote: String?
):BusinessObject() {
    @PrimaryKey(autoGenerate = true) var room_order_id: Int = 0
}