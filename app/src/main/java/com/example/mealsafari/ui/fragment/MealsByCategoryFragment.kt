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
    private lateinit var binding: CategoryFragmentBinding
    private val viewModel: ViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding für das Fragment erstellen
       //
        viewModel.loadMealByCategory("category")
        binding = CategoryFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       viewModel.getMealsByCategory.observe(viewLifecycleOwner){category ->
           binding.recMealsCategory.adapter = MealsByCategoryAdapter(category,viewModel)
       }



    }
}