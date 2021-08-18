package com.aziz.gallery.network.interceptors

import com.aziz.gallery.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MainInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val url = chain.request().url.newBuilder()
            .addQueryParameter("key", BuildConfig.USER_KEY)
            .addQueryParameter("orientation", "vertical")
            .addQueryParameter("image_type", "photo").build()
        request.url(url)
        return chain.proceed(request.build())
    }
}