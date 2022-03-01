package com.example.pokedexpoc.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.response.PokemonContainerResponse
import com.example.core.data.repository.interfaces.PokemonRepositoryInterface
//import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PokemonRepositoryInterface) : ViewModel() {

    private val _listPokemon = MutableLiveData<List<PokemonContainerResponse>>()
    val listPokemon: LiveData<List<PokemonContainerResponse>>
        get() = _listPokemon

    private fun fetchPokemons() {
        viewModelScope.launch {
            repository.listPokemons().collect {
                _listPokemon.postValue(it)
            }
        }
    }
}