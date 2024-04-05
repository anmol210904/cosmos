package com.example.cosmos.data.DI

import com.example.cosmos.Utils.Urls
import com.example.cosmos.api.EarthApi
import com.example.cosmos.api.MarsRoverApi
import com.example.cosmos.presentation.AppPostScreen.AddPostScreen
import com.example.cosmos.repo.AddPostRepo
import com.example.cosmos.repo.ApodRepo
import com.example.cosmos.repo.AuthRepo
import com.example.cosmos.repo.EarthRepo
import com.example.cosmos.repo.GetPostRepo
import com.example.cosmos.repo.MarsRoverRepo
import com.example.cosmos.repo.PostRepo
import com.example.cosmos.repo.ProfileRepo
import com.example.cosmos.viewModel.AddPostViewModel
import com.example.cosmos.viewModel.ApodViewModel
import com.example.cosmos.viewModel.AuthViewModel
import com.example.cosmos.viewModel.EarthViewModel
import com.example.cosmos.viewModel.GetPostViewModel
import com.example.cosmos.viewModel.MarsRoverViewModel
import com.example.cosmos.viewModel.PostViewModel
import com.example.cosmos.viewModel.ProfileViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module{




    //API
    single { provideBaseRetrofit().create(EarthApi :: class.java) }
    single { provideBaseRetrofit().create(MarsRoverApi :: class.java) }




    //repo
    single{
        ApodRepo()

    }

    single { AuthRepo() }
    single { AddPostRepo() }
    single { GetPostRepo() }
    single { ProfileRepo() }
    single{PostRepo()}
    single { EarthRepo(get()) }
    single { MarsRoverRepo(get()) }


    //viewModel

    viewModel{
        ApodViewModel(get())

    }

    viewModel{AuthViewModel(get())}
    viewModel{AddPostViewModel(get())}
    viewModel{ GetPostViewModel(get()) }
    viewModel{ProfileViewModel(get())}
    viewModel{PostViewModel(get())}
    viewModel { EarthViewModel(get()) }
    viewModel{MarsRoverViewModel(get())}
}


fun provideBaseRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

