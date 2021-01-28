package com.example.ministockbitapp.di

import com.example.ministockbitapp.BuildConfig
import com.example.ministockbitapp.BuildConfig.API_KEY
import com.example.ministockbitapp.BuildConfig.BASE_URL
import com.example.ministockbitapp.data.HeaderInterceptor
import com.example.ministockbitapp.utils.data.OkHttpClientFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiModule = module {

    single {
        return@single OkHttpClientFactory.create(
            interceptors = HeaderInterceptor(API_KEY),
            showDebugLog = BuildConfig.DEBUG
        )
    }

    single(named(BASE_URL)) { BASE_URL }
}