package com.example.mealsafari.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mealsafari.R
import com.example.mealsafari.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    // Deklaration der Variablen
    private lateinit var binding: WelcomeFragmentBinding

    // Diese Methode wird aufgerufen, um die View für dieses Fragment zu erstellen.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Das Binding-Objekt für das Fragment erstellen
        binding = WelcomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    // Diese Methode wird aufgerufen, sobald die View für dieses Fragment erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigieren zum HomeFragment, wenn der "Get Started"-Button geklickt wird
        binding.btGetStarted.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}
