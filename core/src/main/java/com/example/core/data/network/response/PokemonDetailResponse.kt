package com.example.core.data.network.response

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val order: Int,
    val stats: PokemonDetailStatsResponse,
    val weight: Int
)
