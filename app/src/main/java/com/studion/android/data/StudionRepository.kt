package com.studion.android.data

import com.studion.android.api.StudionApi
import com.studion.android.data.models.TokenObtainModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudionRepository @Inject constructor(private val studionApi: StudionApi) {

    suspend fun getTokenObtain(tokenObtainModel: TokenObtainModel) =
        studionApi.getTokenObtain(tokenObtainModel)

}