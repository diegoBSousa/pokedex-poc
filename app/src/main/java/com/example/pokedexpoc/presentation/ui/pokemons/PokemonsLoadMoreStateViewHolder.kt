package com.example.pokedexpoc.presentation.ui.pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexpoc.databinding.ItemPokemonLoadMoreStateBinding
import com.example.pokedexpoc.databinding.ShimmerLayoutPokemonItemBinding

class PokemonsLoadMoreStateViewHolder(
    itemBinding: ItemPokemonLoadMoreStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {
    private val binding = ItemPokemonLoadMoreStateBinding.bind(itemView)
    private val progressBarLoadingMore = binding.progressLoadMore
    private val textTryAgainMessage = binding.textTryAgain.also { textView ->
        textView.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBarLoadingMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PokemonsLoadMoreStateViewHolder {
            val itemBinding = ItemPokemonLoadMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return PokemonsLoadMoreStateViewHolder(itemBinding, retry)
        }
    }
}