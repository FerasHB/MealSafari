package com.example.mealsafari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mealsafari.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleOnBackPressed()

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewWelcome) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHost.navController)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]


        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.welcomeFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.detailFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.searchFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.categoryDetailFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.editeFragment -> binding.bottomNavigationView.visibility = View.GONE

                else -> binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

    }


    private fun handleOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.fragmentContainerViewWelcome.findNavController().navigateUp()
               // binding.toolbar.title = PGConstant.EMPTY_STRING.strValue
            }
        }
        onBackPressedDispatcher.addCallback(callback)
    }
}