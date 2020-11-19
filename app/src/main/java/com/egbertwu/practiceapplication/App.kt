package com.egbertwu.practiceapplication

import android.app.Application

import com.egbertwu.practiceapplication.di.networkModule
import com.egbertwu.practiceapplication.di.remoteDataModule
import com.egbertwu.practiceapplication.di.repositoryModule
import com.egbertwu.practiceapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, viewModelModule, remoteDataModule, repositoryModule))
        }
    }
}
