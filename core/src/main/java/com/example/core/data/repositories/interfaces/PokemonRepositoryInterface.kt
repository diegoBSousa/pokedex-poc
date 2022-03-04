package com.example.core.data.repositories.interfaces

import androidx.paging.PagingSource
import com.example.core.domain.model.Pokemon

interface PokemonRepositoryInterface {

    fun listPokemons(query: String ) : PagingSource<Int, Pokemon>
}