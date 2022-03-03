package com.example.core.data.repository.interfaces

interface PokemonsRemoteDataSource<T> {

    suspend fun fetchPokemons(queries: Map<String, String>): T
}