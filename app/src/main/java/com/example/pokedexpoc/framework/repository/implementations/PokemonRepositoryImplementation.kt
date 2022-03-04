package com.example.pokedexpoc.framework.repository.implementations

import androidx.paging.PagingSource
import com.example.pokedexpoc.framework.repository.interfaces.PokemonRepositoryInterface
import com.example.core.domain.model.Pokemon
import com.example.pokedexpoc.framework.paging.PokemonsPagingSource


class PokemonRepositoryImplementation(
    //private val service: PokemonService
    private val remoteDataSource: PokemonsRemoteDataSourceImplementation
) : PokemonRepositoryInterface {

    override fun listPokemons(query: String): PagingSource<Int, Pokemon> {
        return PokemonsPagingSource(remoteDataSource, query)
    }
}