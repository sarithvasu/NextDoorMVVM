package com.example.nextdoormvvm.user.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ApartmentListResponse(
    val apartments: List<Apartment>,
    val cities: List<City>,
    val countries: List<Country>,
    val states: List<State>
): Parcelable

@Parcelize
data class Apartment(
    val ApartmentId: Int,
    val CityId: Int,
    val ApartmentName: String,
    val PinCode: String
):Parcelable

@Parcelize
data class City(
    val StateId: Int,
    val CityId: Int,
    val CityName: String
):Parcelable

@Parcelize
data class Country(
    val CountryId: Int,
    val CountryName: String
):Parcelable

@Parcelize
data class State(
    val CountryId: Int,
    val StateId: Int,
    val StateName: String
):Parcelable
