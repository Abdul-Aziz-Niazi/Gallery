package com.aziz.gallery.network.api

class MainRepository {
    private val apiClient = APIClient.getRetrofit()

    suspend fun searchPhotos(query: String,page:Int) = apiClient.searchPhotos(query,page)
}