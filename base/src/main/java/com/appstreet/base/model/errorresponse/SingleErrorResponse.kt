package com.appstreet.base.model.errorresponse

import com.squareup.moshi.Json

data class SingleErrorResponse(
    @Json(name = "error") val error: String
)