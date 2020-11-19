package com.egbertwu.practiceapplication.di

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        GsonBuilder().create()
    }
    single<GsonConverterFactory> {
        GsonConverterFactory
            .create(get())
    }
    factory {
        UserAgentInterceptor()
    }
    single {
        OkHttpClient().newBuilder().addInterceptor(UserAgentInterceptor()).build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get())
            .build()
    }
}

class UserAgentInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().header(
                "User-Agent",
                "Mozilla/5.0 (Linux; Android 8.0.0; Pixel 2 XL Build/OPD1.170816.004) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Mobile Safari/537.36"
            )
                .build()
        )
    }

}