package com.appstreet.base.extension

import com.appstreet.base.model.Result
import com.appstreet.base.exception.RequestException
import com.appstreet.base.model.errorresponse.DefaultErrorResponse
import com.appstreet.base.model.errorresponse.DynamicErrorResponse
import com.appstreet.base.model.errorresponse.JsonApiErrorResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import timber.log.Timber
import com.squareup.moshi.Moshi
import java.net.*
import java.util.concurrent.TimeoutException
import kotlin.Exception


/**
 * Created by kenny on 15/10/18.
 */
suspend inline fun <T> Deferred<Response<T>>.awaitAndGet(): Result<T?> {
    return try {
        val response = await()
        if (response.isSuccessful) Result.Success(response.body(), response.code())
        else {
            val errorBody = response.errorBody()?.source()?.readUtf8()
            Timber.e(errorBody)
            val moshi = Moshi.Builder().build()

            val jsonAdapter = moshi.adapter(DefaultErrorResponse::class.java)
            val errorResponse = try { jsonAdapter.fromJson(errorBody)?.message } catch (e: Exception) { null }

            val jsonDynamicAdapter = moshi.adapter(DynamicErrorResponse::class.java)
            val jsonDynamicErrorResponse = try { jsonDynamicAdapter.fromJson(errorBody).errors.values.flatten().joinToString(", ") } catch (e: Exception) { null }

            val jsonApiAdapter = moshi.adapter(JsonApiErrorResponse::class.java)
            /*val jsonApiErrorResponse = try { jsonApiAdapter.fromJson(errorBody)?.let { it.message ?: it.errorList[0].detail }
            } catch (e: Exception) { null }*/
            val jsonApiErrorResponse: JsonApiErrorResponse? = try { jsonApiAdapter.fromJson(errorBody) } catch (e: Exception) { null }

            if (jsonApiErrorResponse != null) {
                Result.Failure(
                    RequestException(
                        response.code(),
                        jsonApiErrorResponse.errorList.associate {
                            it.source?.parameter.orEmpty() to it.detail.capitalize() }
                    )
                )
            }
            else {
                val responseMessage = errorResponse ?: jsonDynamicErrorResponse

                Result.Failure(
                    RequestException(
                        response.code(), mapOf("" to responseMessage.orEmpty())
                    )
                )
            }

        }
    } catch (e: Exception) {
        when (e) {
            is UnknownHostException,
            is SocketException,
            is SocketTimeoutException,
            is TimeoutException,
            is ConnectException -> {}
            else -> Timber.tag("Network Request").e(e)
        }
        Result.Failure(e)
    }
}