package com.example.pokedexpoc.framework.di

import android.util.Log
import com.example.core.data.network.response.PokemonContainerResponse
import com.example.pokedexpoc.framework.repository.implementations.PokemonRepositoryImplementation
import com.example.pokedexpoc.framework.repository.implementations.PokemonsRemoteDataSourceImplementation
import com.example.core.data.repositories.interfaces.PokemonRepositoryInterface
import com.example.core.data.repositories.interfaces.PokemonsRemoteDataSourceInterface
import com.example.core.data.services.PokemonService
import com.example.core.usecase.GetPokemonsUseCase
import com.example.pokedexpoc.presentation.MainViewModel
import com.example.pokedexpoc.presentation.ui.pokemons.PokemonsViewHolder
import com.example.pokedexpoc.presentation.ui.pokemons.PokemonsViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private const val OK_HTTP = "Ok Http"

    fun load() {
        loadKoinModules(pokemonsModule() + networkModule() + useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { GetPokemonsUseCase(get()) }

            viewModel {
                PokemonsViewModel(get())
            }
        }
    }
    private fun pokemonsModule() : Module {
        return module {
            single<PokemonRepositoryInterface> {
                PokemonRepositoryImplementation(get())
            }

            single<PokemonsRemoteDataSourceInterface<PokemonContainerResponse>> {
                PokemonsRemoteDataSourceImplementation(get())
            }
        }
    }

    private fun networkModule() : Module {
        return module {
            single<PokemonService> {
                createService(get(), get())
            }

            single {
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            }

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it )
                }

               interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
        }
    }

    private inline fun <reified T>createService(
        factory: Moshi,
        client: OkHttpClient
    ) : T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
            .create(T::class.java)
    }
}