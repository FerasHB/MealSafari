package com.example.mealsafari.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.databinding.FavoriteFragmentBinding
import com.example.mealsafari.ui.Adapter.FavoriteAdapter

class FavoriteFragment:Fragment() {
    private lateinit var binding: FavoriteFragmentBinding
    val viewModel: MealViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteFragmentBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.favoriteMeals.observe(viewLifecycleOwner) { favoriteMeals ->
            // Aktualisiere die UI mit der Liste der Favoriten
            viewModel.addToFavorites(favoriteMeals.first())
           binding.favRecView.adapter = FavoriteAdapter(favoriteMeals,viewModel)
           /* favoriteMeals.forEach {
            viewModel.loadRandomMeal()
                Log.d("test",it.idMeal.toString())
            }*/


        }
    }

}