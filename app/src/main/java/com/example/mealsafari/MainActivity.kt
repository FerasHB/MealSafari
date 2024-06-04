package com.example.mealsafari

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mealsafari.databinding.ActivityMainBinding
import com.example.mealsafari.ui.Adapter.PopularAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Layout-Bindung initialisieren und Layout setzen
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Rücktaste-Handling einrichten
        handleOnBackPressed()

        // BottomNavigationView initialisieren und Hintergrundfarbe festlegen
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.orang))

        // NavHostFragment und NavController initialisieren
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewWelcome) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHost.navController)

        // ViewModel initialisieren
      //  viewModel = ViewModelProvider(this)[ViewModel::class.java]

        // Listener für Zielwechsel hinzufügen
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            // Die Sichtbarkeit der Bottom Navigation View basierend auf dem aktuellen Ziel einstellen
            when (destination.id) {
                R.id.welcomeFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.detailFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.searchFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.categoryDetailFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.editeFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.addFragment -> binding.bottomNavigationView.visibility = View.GONE

                else -> binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
        // Nachtmodus auf Systemeinstellungen festlegen
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)


    }


    // Benutzerdefiniertes Rücktaste-Handling
    private fun handleOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Zurück-Navigation durchführen
                binding.fragmentContainerViewWelcome.findNavController().navigateUp()
            }
        }
        onBackPressedDispatcher.addCallback(callback)
    }


}
