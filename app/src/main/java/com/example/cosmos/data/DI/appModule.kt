package com.example.cosmos.data.DI

import com.example.cosmos.presentation.AppPostScreen.AddPostScreen
import com.example.cosmos.repo.AddPostRepo
import com.example.cosmos.repo.ApodRepo
import com.example.cosmos.repo.AuthRepo
import com.example.cosmos.repo.GetPostRepo
import com.example.cosmos.repo.ProfileRepo
import com.example.cosmos.viewModel.AddPostViewModel
import com.example.cosmos.viewModel.ApodViewModel
import com.example.cosmos.viewModel.AuthViewModel
import com.example.cosmos.viewModel.GetPostViewModel
import com.example.cosmos.viewModel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    //repo
    single{
        ApodRepo()

    }

    single { AuthRepo() }
    single { AddPostRepo() }
    single { GetPostRepo() }
    single { ProfileRepo() }


    //viewModel

    viewModel{
        ApodViewModel(get())

    }

    viewModel{AuthViewModel(get())}
    viewModel{AddPostViewModel(get())}
    viewModel{ GetPostViewModel(get()) }
    viewModel{ProfileViewModel(get())}
}