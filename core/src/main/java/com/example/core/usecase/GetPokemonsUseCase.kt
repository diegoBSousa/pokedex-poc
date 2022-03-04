package com.example.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repositories.interfaces.PokemonRepositoryInterface
import com.example.core.domain.model.Pokemon
import com.example.core.usecase.base.PagingUserCase
import kotlinx.coroutines.flow.Flow

class GetPokemonsUseCase(
    private val pokemonRepository: PokemonRepositoryInterface
) : PagingUserCase<GetPokemonsUseCase.GetPokemonsParams, Pokemon>() {

    data class GetPokemonsParams(val query: String, val pagingConfig: PagingConfig)

    override fun createFlowObservable(params: GetPokemonsParams): Flow<PagingData<Pokemon>> {
        return Pager(config = params.pagingConfig) {
            pokemonRepository.listPokemons(params.query)
        }.flow
    }
}