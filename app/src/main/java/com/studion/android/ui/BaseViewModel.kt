package com.studion.android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studion.android.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {

    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> Response<T>
    ) {
        liveData.postValue(Event.loading())
        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                if (response.body() != null) {
                    liveData.postValue(Event.success(response.body()!!))
                } else if (response.errorBody() != null) {
                    liveData.postValue(Event.error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(e.message?.let { Event.error(it) })
            }
        }
    }

    fun <T> requestWithCallback(
        request: suspend () -> Response<T>,
        response: (Event<T>) -> Unit
    ) {
        response(Event.loading())
        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = request.invoke()

                launch(Dispatchers.Main) {
                    if (res.body() != null) {
                        response(Event.success(res.body()!!))
                    } else if (res.errorBody() != null) {
                        response(Event.error(res.errorBody().toString()))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    response(Event.error(e.message!!))
                }
            }
        }
    }

}