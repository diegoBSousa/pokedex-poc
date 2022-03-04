package com.example.pokedexpoc.framework.repository.interfaces

interface PokemonsRemoteDataSourceInterface<T> {

    suspend fun fetchPokemons(queries: Map<String, String>): T
}