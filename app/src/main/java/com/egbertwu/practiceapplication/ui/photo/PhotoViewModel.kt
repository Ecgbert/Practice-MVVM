package com.egbertwu.practiceapplication.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.egbertwu.practiceapplication.repository.PhotoRepository
import com.egbertwu.practiceapplication.vo.PhotoVO
import org.koin.core.KoinComponent
import org.koin.core.inject

class PhotoViewModel : ViewModel(), KoinComponent {

    private val photoRepository: PhotoRepository by inject()

    val photoLiveData: LiveData<PagingData<PhotoVO>> =
        photoRepository.getPhotoData().cachedIn(viewModelScope).asLiveData()


}