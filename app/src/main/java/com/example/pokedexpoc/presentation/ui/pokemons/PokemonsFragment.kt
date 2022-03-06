package com.example.pokedexpoc.presentation.ui.pokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.core.domain.model.Pokemon
import com.example.pokedexpoc.R
import com.example.pokedexpoc.databinding.FragmentPokemonsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokemonsFragment : Fragment() {

    private var _binding: FragmentPokemonsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: PokemonsViewModel by viewModel()

    private val pokemonsAdapter = PokemonsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPokemonsAdapter()
        observeInitialLoadState()

        lifecycleScope.launch {
            viewModel.pokemonsPagingData(query = "").collect { pagingData ->
                pokemonsAdapter.submitData(pagingData)
            }
        }
        /* *
        * Todo("Remove data test")

        pokemonsAdapter.submitList(
            listOf(
                Pokemon(1, "bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                Pokemon(2, "ivysaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"),
                Pokemon(3, "venusaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"),
                Pokemon(4, "charmander", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"),
                Pokemon(5, "charmeleon", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png"),
                Pokemon(6, "charizard", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png"),
                Pokemon(7, "squirtle", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"),
            )
        )*/
    }

    private fun initPokemonsAdapter() {
        with(binding.pokemonsRecycler) {
            setHasFixedSize(true)
            adapter = pokemonsAdapter
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            pokemonsAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperPokemon.displayedChild = when(loadState.refresh) {
                    is LoadState.Loading -> {
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_POKEMONS
                    }
                    is LoadState.Error -> {
                        setShimmerVisibility(false)
                        binding.includeViewPokemonsErrorState.buttonRetry.setOnClickListener {
                            pokemonsAdapter.refresh()
                        }
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewPokemonsLoadingState.shimmerPokemons.run {
            isVisible = visibility
            if(visibility) {
                startShimmer()
            } else stopShimmer()
        }
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_POKEMONS = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }
    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemons, container, false)
    }*/
}