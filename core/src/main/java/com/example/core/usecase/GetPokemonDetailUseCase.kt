package com.example.core.usecase

import com.example.core.data.repositories.interfaces.PokemonRepositoryInterface
import com.example.core.domain.model.PokemonDetail
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow

interface GetPokemonDetailUseCase {

    operator fun invoke(params: GetPokemonDetailParams): Flow<ResultStatus<PokemonDetail>>

    data class GetPokemonDetailParams(val pokemonId: Int)
}

class GetPokemonDetailUseCaseImpl(
    private val repository: PokemonRepositoryInterface
) : GetPokemonDetailUseCase, UseCase<GetPokemonDetailUseCase.GetPokemonDetailParams, PokemonDetail>() {

    override suspend fun doWork(
        params: GetPokemonDetailUseCase.GetPokemonDetailParams
    ): ResultStatus<PokemonDetail> {
        val pokemon = repository.getPokemon(params.pokemonId)
        return ResultStatus.Success(pokemon)
    }

}