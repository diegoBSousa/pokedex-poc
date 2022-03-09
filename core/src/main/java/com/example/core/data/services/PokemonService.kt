package com.example.core.data.services

import com.example.core.data.network.response.PokemonContainerResponse
import com.example.core.data.network.response.PokemonDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Essa interface para comunicação com a API
 */
interface PokemonService {

    /**
     * Obtem Lista de Pokemons
     */
    @GET("pokemon")
    suspend fun listPokemons(
        @QueryMap
        queries: Map<String, String>
    ) : PokemonContainerResponse

    @GET("pokemon/{pokemonId}/")
    suspend fun getPokemon(
        @Path("pokemonId")
        pokemonId: Int
    ): PokemonDetailResponse
}