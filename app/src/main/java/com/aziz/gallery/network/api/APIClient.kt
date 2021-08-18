package com.aziz.gallery.network.api

import com.aziz.gallery.config.Constants
import com.aziz.gallery.network.interceptors.MainInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    lateinit var apiClient: NetworkInterface

    fun getRetrofit(): NetworkInterface {
        if (::apiClient.isInitialized)
            return apiClient

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(MainInterceptor())
            .addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiClient = retrofit.create(NetworkInterface::class.java)
        return apiClient
    }

}