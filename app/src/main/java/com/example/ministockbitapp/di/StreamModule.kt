package com.example.ministockbitapp.di

import com.example.ministockbitapp.data.stream.StreamDataStore
import com.example.ministockbitapp.data.stream.StreamRepository
import com.example.ministockbitapp.data.stream.remote.StreamWebSocketClient
import com.example.ministockbitapp.utils.data.ScarletService
import com.example.ministockbitapp.viewmodel.StreamViewModel
import com.tinder.scarlet.lifecycle.LifecycleRegistry
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val streamModule = module {
    single {
        ScarletService.createScarletService(
            StreamWebSocketClient::class.java,
            get(),
            "wss://streamer.cryptocompare.com/v2"
        )
    }

    single<StreamRepository> { StreamDataStore(get()) }

    viewModel { StreamViewModel(get(), get()) }
}