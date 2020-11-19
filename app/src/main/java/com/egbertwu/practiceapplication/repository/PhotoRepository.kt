package com.egbertwu.practiceapplication.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.egbertwu.practiceapplication.api.PhotoApiService
import com.egbertwu.practiceapplication.vo.PhotoVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

class PhotoRepository : KoinComponent {

    private val photoService by inject<PhotoApiService>()

    fun getPhotoData(): Flow<PagingData<PhotoVO>> {
        return Pager(pagingConfig) {
            PhotoItemPagingSource(photoService)
        }.flow.map { pagingData ->
            pagingData.map {
                PhotoVO(it.mId, it.mTitle, it.mUrl, it.mThumbnailUrl)
            }
        }
    }

    private val pagingConfig = PagingConfig(
        pageSize = 32,
        enablePlaceholders = false,
        prefetchDistance = 16
    )

}