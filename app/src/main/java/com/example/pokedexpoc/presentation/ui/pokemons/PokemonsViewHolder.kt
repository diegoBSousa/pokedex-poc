package com.example.pokedexpoc.presentation.ui.pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Pokemon
import com.example.pokedexpoc.R
import com.example.pokedexpoc.databinding.ItemPokemonBinding
import com.github.florent37.glidepalette.BitmapPalette.Profile.MUTED_DARK
import com.github.florent37.glidepalette.BitmapPalette.Profile.VIBRANT
import com.github.florent37.glidepalette.GlidePalette

class PokemonsViewHolder(
    itemPokemonBinding: ItemPokemonBinding
) : RecyclerView.ViewHolder(itemPokemonBinding.root) {

    private val pokemonName = itemPokemonBinding.pokemonName
    private val pokemonImage = itemPokemonBinding.pokemonImage
    private val pokemonCard = itemPokemonBinding.pokemonCard

    fun bind(pokemon: Pokemon) {
        pokemonName.text = pokemon.name
        Glide.with(itemView)
            .load(pokemon.imageUrl)
            .listener(
                GlidePalette.with(pokemon.imageUrl)
                    .use(MUTED_DARK)
                    .intoBackground(pokemonCard)

                    .use(VIBRANT)
                    .intoTextColor(pokemonName)
            )
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
