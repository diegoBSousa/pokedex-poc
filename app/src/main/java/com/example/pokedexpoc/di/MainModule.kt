package com.example.pokedexpoc.di

import com.example.pokedexpoc.presentation.MainRepository
import com.example.pokedexpoc.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        MainRepository()
    }

    viewModel {
        MainViewModel(
            repository = get()
        )
    }
}