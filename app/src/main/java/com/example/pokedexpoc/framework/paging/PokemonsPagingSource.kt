package com.example.pokedexpoc.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedexpoc.framework.repository.implementations.PokemonsRemoteDataSourceImplementation
import com.example.core.domain.model.Pokemon
import com.example.core.domain.model.toPokemonModel

class PokemonsPagingSource(
    private val remoteDataSource: PokemonsRemoteDataSourceImplementation,
    private val query: String /*Isso aqui preciso verificar na api se utilizarei mesmo*/
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val offset = params.key ?: 0
            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            val response = remoteDataSource.fetchPokemons(queries)

            var responseNext = response.next
            var responseOffSet = response.next?.takeLast(4)?.filter { it.isDigit() }?.toInt() ?: 0
            var responsePrevious = response.previous
            var responseCount = response.results.size
            var responseTotal = response.count

            LoadResult.Page(
                data = response.results.map { it.toPokemonModel() },
                prevKey = null,
                nextKey = if(responseOffSet <= responseTotal) {
                    responseOffSet
                } else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}