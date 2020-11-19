package com.egbertwu.practiceapplication.di

import com.egbertwu.practiceapplication.ui.photo.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PhotoViewModel() }
}