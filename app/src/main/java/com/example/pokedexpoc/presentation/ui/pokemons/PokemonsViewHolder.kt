package com.example.pokedexpoc.presentation.ui.pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Pokemon
import com.example.pokedexpoc.R
import com.example.pokedexpoc.databinding.ItemPokemonBinding

class PokemonsViewHolder(
    itemPokemonBinding: ItemPokemonBinding
) : RecyclerView.ViewHolder(itemPokemonBinding.root) {

    private val pokemonName = itemPokemonBinding.pokemonName
    private val pokemonImage = itemPokemonBinding.pokemonImage

    fun bind(pokemon: Pokemon) {
        pokemonName.text = pokemon.name
        Glide.with(itemView)
            .load(pokemon.imageUrl)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .into(pokemonImage)
    }

    companion object {
        fun create(parent: ViewGroup): PokemonsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPokemonBinding.inflate(inflater, parent, false)
            return PokemonsViewHolder(itemBinding)
        }
    }

}
