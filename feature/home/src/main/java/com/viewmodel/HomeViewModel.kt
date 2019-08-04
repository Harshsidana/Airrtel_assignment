package com.appstreet.home.viewmodel


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appstreet.base.core.BaseScopeViewModel
import com.appstreet.base.extension.awaitAndGet
import com.appstreet.base.model.DataState
import com.appstreet.base.model.DataState.*
import com.appstreet.base.model.Result
import com.appstreet.home.model.response.ApiReponse
import com.appstreet.home.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(
    val app: Application,
    private val homeRepo: HomeRepository
) : BaseScopeViewModel(app) {

    private lateinit var homeLiveData: MutableLiveData<DataState<List<ApiReponse>>>


    fun getCardCellDetails(): LiveData<DataState<List<ApiReponse>>>{
        if (!::homeLiveData.isInitialized) {
            homeLiveData = MutableLiveData()
            getBroadcastItemRequestDetail()
        }
        return homeLiveData
    }

    private fun CoroutineScope.getBroadcastItemRequestDetail() {
        launch {
            val result = homeRepo.fetchData().awaitAndGet()
            when (result) {
                is Result.Success -> {
                    result.body?.run {
                        homeLiveData.postValue(Success(this))
                    }
                }


            }
        }
    }
}




