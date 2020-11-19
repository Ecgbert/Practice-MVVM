package com.egbertwu.practiceapplication.api

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("albumId")
    val mAlbumId: Int,
    @SerializedName("id")
    val mId: Int,
    @SerializedName("title")
    val mTitle: String,
    @SerializedName("url")
    val mUrl: String,
    @SerializedName("thumbnailUrl")
    val mThumbnailUrl: String
)