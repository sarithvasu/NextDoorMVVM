package com.example.nextdoormvvm.internal

data class HttpResponseMessage(
    val HeaderMessageUri: String = "",
    val Id: Int = -1,
    val Message: String = "",
    val StatusCode: Int = -1
)