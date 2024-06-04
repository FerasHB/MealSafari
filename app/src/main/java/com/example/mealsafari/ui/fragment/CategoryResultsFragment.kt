package com.example.mealsafari.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mealsafari.ViewModel
import com.example.mealsafari.databinding.CategoryResultsFragmentBinding
import com.example.mealsafari.ui.Adapter.CategoryResultsAdapter

class CategoryResultsFragment : Fragment() {
    private lateinit var binding: CategoryResultsFragmentBinding
    private val viewModel: ViewModel by activityViewModels()

    // Wird aufgerufen, um die Ansicht für dieses Fragment zu erstellen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Aufblasen des Layouts für dieses Fragment
        binding = CategoryResultsFragmentBinding.inflate(inflater)


        // Rückgabe der aufgeblasenen Ansicht
        return binding.root
    }

    // Wird aufgerufen, nachdem die Ansicht für dieses Fragment erstellt wurde
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Beobachten der Liste aller Mahlzeitenkategorien
        viewModel.allMealCategories.observe(viewLifecycleOwner) { categories ->
            // Aktualisieren des RecyclerView-Adapters mit den neuen Mahlzeitenkategorien
            binding.rvMealsByCat.adapter = CategoryResultsAdapter(categories, viewModel)
        }
    }
}
