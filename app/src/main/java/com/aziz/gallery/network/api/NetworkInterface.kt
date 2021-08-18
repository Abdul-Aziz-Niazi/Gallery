package com.aziz.gallery.network.api

import com.aziz.gallery.network.models.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {
    @GET(".")
    suspend fun searchPhotos(@Query("q") query: String, @Query("page") page:Int): PhotoResponse
}