package com.example.nextdoormvvm.buyer.network.post

import com.example.nextdoormvvm.internal.BusinessObject

data class PostPurchaseOrder(
    val order: List<Order>,
    val Payment: UPIPayment?
):BusinessObject()


data class UPIPayment(
    var BuyerId: Int = 0,
    var SellerId: Int = 0,
    var ChefId: Int = 0,
    var ApprovalReferenceNumberBeneficiary: String = "",
    var CurrencyCode: String = "",
    var ResponseCode: String = "",
    var TransactionAmount: Int = 0,
    var TransactionId: String = "",
    var TransactionNote: String = "",
    var TransactionReferenceId: String = "",
    var TransactionStatus: String = ""
)

data class Order(
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
):BusinessObject()