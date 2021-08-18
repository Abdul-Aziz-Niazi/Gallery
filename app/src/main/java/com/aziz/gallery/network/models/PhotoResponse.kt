package com.aziz.gallery.network.models

data class PhotoResponse(val total: Int, val totalHits: Int, val hits: ArrayList<Photo>)