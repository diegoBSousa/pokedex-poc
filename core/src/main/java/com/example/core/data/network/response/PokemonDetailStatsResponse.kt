package com.example.core.data.network.response

data class PokemonDetailStatsResponse(
    val base_stat: Int,
    val effort: Int,
    val stat: PokemonDetailStatsStat
)