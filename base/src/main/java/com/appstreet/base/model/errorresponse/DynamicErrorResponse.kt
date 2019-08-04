package com.appstreet.base.model.errorresponse

import com.squareup.moshi.Json

data class DynamicErrorResponse(
    @Json(name = "errors") val errors: Map<String, List<String>>
)