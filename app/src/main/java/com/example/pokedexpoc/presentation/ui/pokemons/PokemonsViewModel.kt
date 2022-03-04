package com.example.pokedexpoc.presentation.ui.pokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.domain.model.Pokemon
import com.example.core.usecase.GetPokemonsUseCase
import kotlinx.coroutines.flow.Flow

class PokemonsViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    fun pokemonsPagingData(query: String): Flow<PagingData<Pokemon>> {
        return getPokemonsUseCase(
            GetPokemonsUseCase.GetPokemonsParams(query, getPageConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPageConfig() = PagingConfig(pageSize = 20)
}