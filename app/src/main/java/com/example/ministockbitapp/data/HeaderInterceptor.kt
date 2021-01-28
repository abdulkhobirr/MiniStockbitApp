package com.example.ministockbitapp.data

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
        private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
                .addHeader("authorization", apiKey)
                .method(original.method, original.body)
                .build()

        return chain.proceed(request)
    }
}