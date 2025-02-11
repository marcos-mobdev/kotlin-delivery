package br.com.appforge.kotlindelivery.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import br.com.appforge.kotlindelivery.R
import br.com.appforge.kotlindelivery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        initializeNavigation()
    }

    private fun initializeNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerViewMain) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNavigationViewMain,
            navHostFragment.navController
        )
    }


}