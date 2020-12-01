package com.studion.android.di

import android.content.Context
import com.studion.android.api.StudionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(StudionApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideStudionApi(retrofit: Retrofit): StudionApi =
        retrofit.create(StudionApi::class.java)


//        @Provides
//        @Singleton
//        fun provideAppDatabase(@ApplicationContext appContext: Context) =
//            Room.databaseBuilder(
//                appContext,
//                AppDatabase::class.java,
//                "posts"
//            )
//                .fallbackToDestructiveMigration()
//                .build()
//
//        @Provides
//        @Singleton
//        fun provideChannelDao(appDatabase: AppDatabase) = appDatabase.placeholderDao()
}

