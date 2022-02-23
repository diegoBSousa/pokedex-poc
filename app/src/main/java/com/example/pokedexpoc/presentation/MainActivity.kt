package com.example.pokedexpoc.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.pokedexpoc.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Este 'by viewModel()' precisa ter o viewModel() do Koin carregado
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
