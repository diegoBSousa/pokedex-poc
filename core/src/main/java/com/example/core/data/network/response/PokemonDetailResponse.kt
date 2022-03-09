package com.example.core.data.network.response

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val order: Int,
    val stats: List<PokemonDetailStatsResponse>,
    val height: Int,
    val weight: Int
)
