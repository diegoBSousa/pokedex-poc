package com.example.pokedexpoc.framework.paging

import android.media.audiofx.DynamicsProcessing
import android.os.LimitExceededException
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
                "offset" to offset.toString(),
                "limit" to LIMIT.toString()
            )

            val response = remoteDataSource.fetchPokemons(queries)

            //var responseNext = response.next
            var nextOffSet = response.next?.takeLast(4)?.filter { it.isDigit() }?.toInt() ?: 0

            //var responsePrevious = response.previous
            var previousOffset = response.previous?.takeLast(4)?.filter { it.isDigit() }?.toInt() ?: 0

            //var responseOffSet = nextOffSet - LIMIT

            //if(previousOffset == 0) {
            //    responseOffSet += LIMIT
            //}

            //if(nextOffSet == 0) {
            //    responseOffSet = CEIL_LIMIT * 20
            //}

            //var responseCount = response.results.size
            var responseTotal = response.count
            var responseOffSet = params.key ?: 1

            LoadResult.Page(
                data = response.results.map { it.toPokemonModel() },
                prevKey = null,
                nextKey = if(responseOffSet < responseTotal) {
                    responseOffSet + LIMIT
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
        private const val CEIL_LIMIT = 20
    }
}