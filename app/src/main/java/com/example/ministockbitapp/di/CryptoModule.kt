package com.example.ministockbitapp.di

import com.example.ministockbitapp.BuildConfig.BASE_URL
import com.example.ministockbitapp.data.crypto.CryptoDataStore
import com.example.ministockbitapp.data.crypto.CryptoRepository
import com.example.ministockbitapp.data.crypto.remote.CryptoApi
import com.example.ministockbitapp.data.crypto.remote.CryptoApiClient
import com.example.ministockbitapp.utils.data.ApiService
import com.example.ministockbitapp.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val cryptoModule = module {

    single {
        ApiService.createReactiveService(
            CryptoApiClient::class.java,
            get(),
            get(named(BASE_URL))
        )
    }

    single { CryptoApi(get()) }

    single<CryptoRepository> { CryptoDataStore(get()) }

    viewModel { CryptoViewModel(get(), get()) }
}