package com.example.mealsafari.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.HomeFragmentBinding
import com.example.mealsafari.ui.Adapter.CategoryHomeAdapter
import com.example.mealsafari.ui.Adapter.PopularAdapter
import syntax.com.playground.data.model.meal.Meal

class HomeFragment : Fragment() {

    // Deklaration der Variablen
    private lateinit var binding: HomeFragmentBinding
    private val viewModel: ViewModel by activityViewModels()

    // Diese Methode wird aufgerufen, um die View für dieses Fragment zu erstellen.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Das Binding-Objekt für das Fragment erstellen
        binding = HomeFragmentBinding.inflate(inflater)

        // Rückgabe der aufgeblasenen Ansicht
        return binding.root
    }

    // Diese Methode wird aufgerufen, sobald die View für dieses Fragment erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mealId = arguments?.getLong("mealId") ?: 0L
        // Laden des zufälligen Essens, aller Mahlzeitenkategorien und beliebter Mahlzeiten
        viewModel.loadRandomMeal()
        viewModel.loadAllMealCategories()
        viewModel.loadPopularMeal("Dessert")


        // Beobachten des LiveData-Objekts meals aus dem ViewModel
        viewModel.meals.observe(viewLifecycleOwner) { mealObj: Meal ->
            // Laden des Bildes des zufälligen Essens in das ImageView
            binding.imgRandomMeal.load(mealObj.image)
            // Klicken auf das Zufällige-Essen-Kartenlayout
            binding.randomMealCard.setOnClickListener {
                // Navigieren zur Detailansicht des zufälligen Essens
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                        .setMealId(mealObj.idMeal)
                )
                Log.e(ContentValues.TAG, "RandomMeal: Error Load Data from Api:")
            }
        }



        // Klicken auf die Such-Schaltfläche
        binding.ivSearch.setOnClickListener {
            // Navigieren zur Suchansicht
            findNavController().navigate(R.id.searchFragment)
        }


        // Beobachten des LiveData-Objekts popularMeal aus dem ViewModel
        viewModel.popularMeal.observe(viewLifecycleOwner) { popularMeals ->
            // Aktualisieren des RecyclerView-Adapters mit den beliebten Mahlzeiten
            binding.recViewMealPopular.adapter = PopularAdapter(popularMeals, viewModel)
        }

        // Beobachten des LiveData-Objekts getMealsByCategory aus dem ViewModel
        viewModel.getMealsByCategory.observe(viewLifecycleOwner) { mealCategories ->
            // Aktualisieren des RecyclerView-Adapters mit den Mahlzeitenkategorien
            binding.recViewCategories.adapter = CategoryHomeAdapter(mealCategories, viewModel)
        }

        // Klicken auf den RecyclerView für die Mahlzeitenkategorien
        binding.recViewCategories.setOnClickListener {
            // Navigieren zur Fragment für Mahlzeitenkategorien
            findNavController().navigate(R.id.categoryMealsFragment)
        }
    }
}
