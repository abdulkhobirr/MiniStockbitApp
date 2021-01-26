package com.example.ministockbitapp.di

import com.example.ministockbitapp.BuildConfig
import com.example.ministockbitapp.BuildConfig.BASE_URL
import com.example.ministockbitapp.utils.data.OkHttpClientFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiModule = module {

    single {
        return@single OkHttpClientFactory.create(
            showDebugLog = BuildConfig.DEBUG,
            context = androidContext()
        )
    }

    single(named(BASE_URL)) { BASE_URL }
}