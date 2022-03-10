package com.example.pokedexpoc.framework.di

import com.example.core.usecase.GetPokemonDetailUseCase
import com.example.core.usecase.GetPokemonDetailUseCaseImpl
import com.example.core.usecase.GetPokemonsUseCase
import com.example.pokedexpoc.presentation.ui.detail.DetailViewModel
import com.example.pokedexpoc.presentation.ui.pokemons.PokemonsViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule() + useCaseModule())
    }

    private fun viewModelModule() : Module {
        return module {
            factory {
                //HomeViewModel(get())
                PokemonsViewModel(get())
            }

            factory {
                DetailViewModel(get())
            }
        }
    }

    private fun useCaseModule(): Module {
        return module {
            factory { GetPokemonsUseCase(get()) }

            factory<GetPokemonDetailUseCase> { GetPokemonDetailUseCaseImpl(get()) }
        }
    }
}