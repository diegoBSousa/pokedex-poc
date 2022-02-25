package com.example.core.data.repository.interfaces

import com.example.core.data.network.response.PokemonContainerResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepositoryInterface {
    suspend fun listPokemons() : Flow<List<PokemonContainerResponse>>
}