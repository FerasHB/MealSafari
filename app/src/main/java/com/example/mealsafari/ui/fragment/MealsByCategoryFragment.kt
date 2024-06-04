package com.example.mealsafari.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mealsafari.ViewModel
import com.example.mealsafari.databinding.CategoryFragmentBinding
import com.example.mealsafari.ui.Adapter.MealsByCategoryAdapter

class MealsByCategoryFragment : Fragment() {

    // Deklaration der Variablen
    private lateinit var binding: CategoryFragmentBinding
    private val viewModel: ViewModel by activityViewModels()

    // Diese Methode wird aufgerufen, um die View für dieses Fragment zu erstellen.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Laden der Mahlzeiten nach einer bestimmten Kategorie
        viewModel.loadMealByCategory("category")

        // Das Binding-Objekt für das Fragment erstellen
        binding = CategoryFragmentBinding.inflate(inflater)

        // Rückgabe der aufgeblasenen Ansicht
        return binding.root
    }

    // Diese Methode wird aufgerufen, sobald die View für dieses Fragment erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Beobachten des LiveData-Objekts getMealsByCategory aus dem ViewModel
        viewModel.getMealsByCategory.observe(viewLifecycleOwner) { category ->
            // Aktualisieren des RecyclerView-Adapters mit den Mahlzeiten in der Kategorie
            binding.recMealsCategory.adapter = MealsByCategoryAdapter(category, viewModel)
        }
    }
}
