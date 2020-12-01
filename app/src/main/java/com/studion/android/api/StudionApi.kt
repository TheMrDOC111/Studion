package com.studion.android.api

import com.studion.android.data.models.TokenObtainModel
import retrofit2.Response
import retrofit2.http.*

interface StudionApi {

    companion object {
        const val BASE_URL = "http://142.93.100.70/api/"
    }

    @POST("token/")
    suspend fun getTokenObtain(@Body tokenObtainModel: TokenObtainModel): Response<TokenObtainModel>


}