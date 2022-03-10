package com.example.pokedexpoc.presentation.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.pokedexpoc.R
import com.example.pokedexpoc.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailViewArg = args.detailViewArg

        binding.pokemonImage.run {
            transitionName = detailViewArg.name
            Glide.with(context)
                .load(detailViewArg.imageUrl)
                .fallback(R.drawable.ic_baseline_broken_image_24)
                .into(this)
        }

        setSharedElementTransitionOnEnter()

        /**
         * Testing ViewModel
         */
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            val logResult = when(uiState) {
                DetailViewModel.UiState.Loading -> "Loading Pokemon Details..."
                is DetailViewModel.UiState.Success -> uiState.pokemonDetail.toString()
                DetailViewModel.UiState.Error -> "Error Gettin Pokemon Detais"
            }

            Log.d(DetailFragment::class.simpleName, logResult)
        }

        viewModel.getPokemon(detailViewArg.id)
    }

    // Defines animation as 'move'
    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
                sharedElementEnterTransition = this
            }
    }

}