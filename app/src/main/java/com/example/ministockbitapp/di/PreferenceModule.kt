package com.example.ministockbitapp.di

import com.example.ministockbitapp.utils.pref.PrefManager
import com.example.ministockbitapp.utils.pref.PreferenceManagerImpl
import com.example.ministockbitapp.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PREFERENCE_NAME = "preference_name"

val preferenceModule = module {

    single(named(PREFERENCE_NAME)) { "Pref Name" }

    single<PrefManager> { PreferenceManagerImpl(get(), get(named(PREFERENCE_NAME))) }

    viewModel { LoginViewModel(get()) }
}