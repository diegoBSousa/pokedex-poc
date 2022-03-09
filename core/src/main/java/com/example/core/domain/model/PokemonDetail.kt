package com.example.core.domain.model

import com.example.core.data.network.response.PokemonDetailResponse
import com.example.core.data.network.response.PokemonResponse

data class PokemonDetail(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val order: Int,
    val height: Int,
    val weight: Int,
)

fun PokemonDetailResponse.toPokemonDetail() : PokemonDetail {
    return PokemonDetail(
        id = this.id,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/"
            + "pokemon/other/official-artwork/${id}.png",
        name = this.name,
        order = this.order,
        height = this.height,
        weight = this.weight
    )
}
