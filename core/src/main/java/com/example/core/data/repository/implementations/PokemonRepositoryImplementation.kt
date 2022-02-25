package com.example.core.data.repository.implementations

import com.example.core.data.network.response.PokemonContainerResponse
import com.example.core.data.repository.interfaces.PokemonRepositoryInterface
import com.example.core.data.services.PokemonService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PokemonRepositoryImplementation(
    private val service: PokemonService
) : PokemonRepositoryInterface {

    override suspend fun listPokemons(): Flow<List<PokemonContainerResponse>> = flow {
        val pokemonList = service.listPokemons()
        emit(pokemonList)
    }
}