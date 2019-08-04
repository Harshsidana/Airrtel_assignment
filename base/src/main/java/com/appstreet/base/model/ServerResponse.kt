package com.appstreet.base.model

import com.squareup.moshi.Json

data class ServerResponse<T>(
    @Json(name = "status") val status: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "data") val data: T,
    @Json(name = "error") val error: String,
    @Json(name = "errorCode") val errorCode: Int
)