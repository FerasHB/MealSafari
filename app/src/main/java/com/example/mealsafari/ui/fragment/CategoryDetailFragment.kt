package com.example.mealsafari.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.databinding.CategoryDetailFragmentBinding
import com.example.mealsafari.ui.Adapter.CategoryDetailAdapter

class CategoryDetailFragment : Fragment() {
    private lateinit var binding: CategoryDetailFragmentBinding
    private val viewModel: MealViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CategoryDetailFragmentBinding.inflate(inflater)

        viewModel.loadMealByCategory("")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.allMealCategories.observe(viewLifecycleOwner) {
            binding.rvMealsByCat.adapter = CategoryDetailAdapter(it, viewModel)

        }

    }
}