package com.example.core.data.services

import com.example.core.data.network.response.PokemonContainerResponse
import retrofit2.http.GET

/**
 * Essa interface para comunicação com a API
 */
interface PokemonService {

    /**
     * Obtem Lista de Pokemons
     */
    @GET("pokemon")
    suspend fun listPokemons() : List<PokemonContainerResponse>
}