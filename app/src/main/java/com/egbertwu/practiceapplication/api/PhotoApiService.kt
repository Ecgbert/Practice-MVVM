package com.egbertwu.practiceapplication.api

import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET("/photos")
    suspend fun getPhotos(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<PhotoResponse>
}