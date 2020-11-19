package com.egbertwu.practiceapplication.repository

import androidx.paging.PagingSource
import com.egbertwu.practiceapplication.api.PhotoApiService
import com.egbertwu.practiceapplication.api.PhotoResponse

class PhotoItemPagingSource(private val photoService: PhotoApiService) : PagingSource<Int, PhotoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        return try {
            val key = params.key ?: 0
            val items = photoService.getPhotos(key, params.loadSize)
            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = items.lastOrNull()?.mId
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
