package com.example.core.data.repositories.interfaces

interface PokemonsRemoteDataSourceInterface<T> {

    suspend fun fetchPokemons(queries: Map<String, String>): T
}