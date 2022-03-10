package com.example.pokedexpoc.presentation.ui.pokemons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Pokemon
import com.example.pokedexpoc.R
import com.example.pokedexpoc.databinding.ItemPokemonBinding
import com.example.pokedexpoc.util.OnPokemonItemClick
import com.github.florent37.glidepalette.BitmapPalette.Profile.MUTED_DARK
import com.github.florent37.glidepalette.BitmapPalette.Profile.MUTED_LIGHT
import com.github.florent37.glidepalette.BitmapPalette.Profile.VIBRANT
import com.github.florent37.glidepalette.BitmapPalette.Profile.VIBRANT_LIGHT
import com.github.florent37.glidepalette.GlidePalette

class PokemonsViewHolder(
    itemPokemonBinding: ItemPokemonBinding,
    private val onItemClick: OnPokemonItemClick
) : RecyclerView.ViewHolder(itemPokemonBinding.root) {

    private val pokemonName = itemPokemonBinding.pokemonName
    private val pokemonImage = itemPokemonBinding.pokemonImage
    private val pokemonCard = itemPokemonBinding.pokemonCard

    fun bind(pokemon: Pokemon) {
        pokemonName.text = pokemon.name
        pokemonImage.transitionName = pokemon.name

        Glide.with(itemView)
            .load(pokemon.imageUrl)
            .listener(
                GlidePalette.with(pokemon.imageUrl)
                    .use(MUTED_DARK)
                    .intoBackground(pokemonCard)

                    //.use(MUTED_LIGHT)
                    //.intoTextColor(pokemonName)
            )
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .into(pokemonImage)

        itemView.setOnClickListener {
            onItemClick.invoke(pokemon, pokemonImage)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: OnPokemonItemClick
        ): PokemonsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPokemonBinding.inflate(inflater, parent, false)
            return PokemonsViewHolder(itemBinding, onItemClick)
        }
    }

}
