package com.example.pokedexpoc.framework.repository.implementations

import com.example.core.data.network.response.PokemonContainerResponse
import com.example.core.data.repositories.interfaces.PokemonsRemoteDataSourceInterface
import com.example.core.data.services.PokemonService
import com.example.core.domain.model.PokemonDetail
import com.example.core.domain.model.toPokemonDetail

class  PokemonsRemoteDataSourceImplementation(
    private val service: PokemonService
) : PokemonsRemoteDataSourceInterface<PokemonContainerResponse> {

    override suspend fun fetchPokemons(queries: Map<String, String>): PokemonContainerResponse {
        return service.listPokemons(queries)
    }

    override suspend fun getPokemon(pokemonId: Int): PokemonDetail {
        return service.getPokemon(pokemonId).toPokemonDetail()
    }
}