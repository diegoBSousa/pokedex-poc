package com.example.core.data.repository.interfaces

import androidx.paging.PagingSource
import com.example.core.data.network.response.PokemonContainerResponse
import com.example.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepositoryInterface {
    //suspend fun listPokemons() : Flow<List<PokemonContainerResponse>>
    fun listPokemons() : PagingSource<Int, Pokemon>
}