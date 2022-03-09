package com.example.core.data.repositories.interfaces

import com.example.core.data.network.response.PokemonDetailResponse
import com.example.core.domain.model.PokemonDetail

interface PokemonsRemoteDataSourceInterface<T> {

    suspend fun fetchPokemons(queries: Map<String, String>): T

    suspend fun getPokemon(pokemonId: Int): PokemonDetail
}