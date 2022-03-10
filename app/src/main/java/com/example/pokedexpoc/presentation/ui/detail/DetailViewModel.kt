package com.example.pokedexpoc.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.PokemonDetail
import com.example.core.usecase.GetPokemonDetailUseCase
import com.example.core.usecase.base.ResultStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun getPokemon(pokemonId: Int) = viewModelScope.launch{
        getPokemonDetailUseCase(GetPokemonDetailUseCase.GetPokemonDetailParams(pokemonId))
            .watchStatus()
    }

    private fun Flow<ResultStatus<PokemonDetail>>.watchStatus() = viewModelScope.launch {
        collect { status ->
            _uiState.value = when (status) {
                ResultStatus.Loading -> UiState.Loading
                is ResultStatus.Success -> UiState.Success(status.data)
                is ResultStatus.Error -> UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val pokemonDetail: PokemonDetail): UiState()
        object Error: UiState()
    }
}