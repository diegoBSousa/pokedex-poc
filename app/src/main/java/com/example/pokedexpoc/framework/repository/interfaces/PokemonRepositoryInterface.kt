package com.example.pokedexpoc.framework.repository.interfaces

import androidx.paging.PagingSource
import com.example.core.domain.model.Pokemon

interface PokemonRepositoryInterface {
    //suspend fun listPokemons() : Flow<List<PokemonContainerResponse>>
    fun listPokemons(query: String ) : PagingSource<Int, Pokemon>
}