package com.appstreet.base.model.errorresponse

import com.squareup.moshi.Json


/*
{
    "errors": [
        {
            "status": 422,
            "code": 40001,
            "title": "Could not be processed",
            "message": "Note tidak boleh kosong, Requested amount Total biaya tidak boleh lebih besar dari sisa saldo klaim",
            "detail": "Parameter is wrong"
        }
    ]
}
 */
data class JsonApiErrorResponse(
    @Json(name = "message") val message: String?,
    @Json(name = "errors") val errorList: List<Error>
) {
    data class Error(
        @Json(name = "title") val title: String,
        @Json(name = "message") val message: String?,
        @Json(name = "detail") val detail: String,
        @Json(name = "source") val source: Source?
    )

    data class Source(
        @Json(name = "parameter") val parameter: String
    )
}