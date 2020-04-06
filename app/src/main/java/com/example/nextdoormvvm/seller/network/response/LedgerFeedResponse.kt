package com.example.nextdoormvvm.seller.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LedgerFeedResponse(
    val currentYearLedgerSummery: CurrentYearLedgerSummery,
    val currentMonthLedgerSummery: List<CurrentMonthLedgerSummery>,
    val currentMonthTransactionDetails: List<CurrentMonthTransactionDetail>

): Parcelable

@Parcelize
data class CurrentMonthLedgerSummery(
    val AmountPaid: Int,
    val Balance: Int,
    val PreviousBalance: Int,
    val ServiceCharge: Int
):Parcelable

@Parcelize
data class CurrentMonthTransactionDetail(
    val DeliveryEndDateTime: String,
    val DishName: String,
    val FullName: String,
    val OrderTotal :Int,
    val ServiceCharge:Int,
    val OrderId: Int
):Parcelable

@Parcelize
data class CurrentYearLedgerSummery(
    val PendingServiceCharge: Int,
    val TotalCodAmount: Int,
    val TotalDigitalAmount: Int,
    val TotalOrderAmount: Int,
    val TotalServiceCharge: Int,
    val TotalServiceChargePaid: Int
):Parcelable