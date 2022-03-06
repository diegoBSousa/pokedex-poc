package com.example.pokedexpoc.presentation.ui.pokemons

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PokemonsLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PokemonsLoadMoreStateViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PokemonsLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: PokemonsLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}