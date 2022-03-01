package com.example.pokedexpoc.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

import com.example.pokedexpoc.R
import com.example.pokedexpoc.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    // Este 'by viewModel()' precisa ter o viewModel() do Koin carregado
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = navHostFragment.navController
        binding.bottomNavMain.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.pokemonsFragment, R.id.favoritesFragment, R.id.aboutFragment)
        )

        binding.toolbarApp.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestination) {
                binding.toolbarApp.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            }
        }
    }
}
