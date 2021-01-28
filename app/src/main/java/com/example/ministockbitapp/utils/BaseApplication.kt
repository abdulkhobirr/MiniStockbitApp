package com.example.ministockbitapp.utils

import android.app.Application
import android.content.Context
import com.example.ministockbitapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class BaseApplication : Application() {
    companion object {
        var context: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        context = this

        startKoin {
            androidContext(this@BaseApplication)
            modules(getDefinedModules())
        }
    }

    private fun getDefinedModules(): List<Module> {
        return listOf(
            cryptoModule,
            apiModule,
            rxModule,
            preferenceModule
        )
    }
}