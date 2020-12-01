package com.studion.android.ui.authorization

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.studion.android.data.StudionRepository
import com.studion.android.data.models.TokenObtainModel
import com.studion.android.ui.BaseViewModel
import com.studion.android.utils.Event

class AuthorizationViewModel @ViewModelInject constructor(
    private val repository: StudionRepository
) : BaseViewModel() {
    private val TAG = this.javaClass.simpleName

    fun authorization(model: TokenObtainModel, callback: (data: Event<TokenObtainModel>) -> Unit) {
        requestWithCallback({
            repository.getTokenObtain(model)
        }) {
            Log.d(TAG, "authorization: ${it.data}")
            callback(it)
        }
    }
}