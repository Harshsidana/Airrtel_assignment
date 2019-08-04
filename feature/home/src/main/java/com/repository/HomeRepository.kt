package com.appstreet.home.repository

import com.appstreet.home.model.response.ApiReponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface HomeRepository {


    fun fetchData(): Deferred<Response<List<ApiReponse>>>
}