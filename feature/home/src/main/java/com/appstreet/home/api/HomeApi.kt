package com.appstreet.home.api


import com.appstreet.home.model.response.ApiReponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("developers")
    fun getData( @Query("language") language: String,
                 @Query("since") since: String): Deferred<Response<List<ApiReponse>>>


}