package com.appstreet.home.repository

import com.appstreet.home.api.HomeApi
import com.appstreet.home.model.response.ApiReponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class HomeRepositoryImpl(
    private val homeApi: HomeApi

    ) : HomeRepository {


    /**
     * Mock Response
     */
    override fun fetchData(): Deferred<Response<List<ApiReponse>>> {
        return homeApi.getData("java","weekly")
    }

}