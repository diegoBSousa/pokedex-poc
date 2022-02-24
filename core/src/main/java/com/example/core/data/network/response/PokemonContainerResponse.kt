package com.example.core.data.network.response

data class PokemonContainerResponse(
    val count: Int,
    val previous: String?,
    val next: String?,
    val results: List<PokemonResponse>
)
