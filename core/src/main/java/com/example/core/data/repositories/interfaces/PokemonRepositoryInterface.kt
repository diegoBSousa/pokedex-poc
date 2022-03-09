package com.example.core.data.repositories.interfaces

import androidx.paging.PagingSource
import com.example.core.domain.model.Pokemon
import com.example.core.domain.model.PokemonDetail

interface PokemonRepositoryInterface {

    fun listPokemons(query: String ) : PagingSource<Int, Pokemon>

    suspend fun getPokemon(pokemonId: Int): PokemonDetail
}