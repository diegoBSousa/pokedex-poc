package com.example.core.domain.model

import com.example.core.data.network.response.PokemonContainerResponse
import com.example.core.data.network.response.PokemonResponse

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String
)

fun PokemonResponse.toPokemonModel() : Pokemon {
    val id = this.url.takeLast(4).filter { it.isDigit() }.toInt()
    return Pokemon(
        id = id,
        name = this.name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    )
}
