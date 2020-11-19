package com.egbertwu.practiceapplication.di

import com.egbertwu.practiceapplication.api.PhotoApiService
import com.egbertwu.practiceapplication.repository.PhotoRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteDataModule = module {
    single<PhotoApiService> {
        get<Retrofit>().create(PhotoApiService::class.java)
    }
}

val repositoryModule = module {
    single { PhotoRepository() }
}