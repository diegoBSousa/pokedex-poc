package com.example.pokedexpoc.framework.di

import android.util.Log
import com.example.core.data.repository.implementations.PokemonRepositoryImplementation
import com.example.core.data.repository.interfaces.PokemonRepositoryInterface
import com.example.core.data.services.PokemonService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private const val OK_HTTP = "Ok Http"

    private fun pokemonsModule() : Module {
        return module {
            single<PokemonRepositoryInterface> {
                PokemonRepositoryImplementation(get())
            }
        }
    }

    private fun networkModule() : Module {
        return module {
            single {
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

    private inline fun createService(
        factory: Moshi,
        client: OkHttpClient
    ) : PokemonService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
            .create(PokemonService::class.java)
    }
}