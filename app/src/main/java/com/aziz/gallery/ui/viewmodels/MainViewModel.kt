package com.aziz.gallery.ui.viewmodels

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aziz.gallery.network.api.MainRepository
import com.aziz.gallery.network.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val mainRepository: MainRepository by lazy { MainRepository() }
    val previewLiveData = MutableLiveData<Bundle>()
    var currentPage = 1
    lateinit var lastQuery: String

    fun searchPhotos(query: String) = liveData(Dispatchers.IO) {
        lastQuery = query
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mainRepository.searchPhotos(query, currentPage)))
        } catch (exception: Exception) {
            emit(Resource.error(null, "Error: ${exception.localizedMessage}"))
        }

    }
}