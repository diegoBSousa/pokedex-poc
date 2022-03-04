package com.example.pokedexpoc.presentation.ui.pokemons

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.Pokemon

class PokemonsAdapter : PagingDataAdapter<Pokemon, PokemonsViewHolder>(differCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        return PokemonsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        getItem(position)?.let { pokemon ->
            holder.bind(pokemon)
        }
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }
}