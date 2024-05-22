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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CategoryResultsFragmentBinding.inflate(inflater)

        viewModel.loadMealByCategory("")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.allMealCategories.observe(viewLifecycleOwner) {
            binding.rvMealsByCat.adapter = CategoryResultsAdapter(it, viewModel)

        }

    }
}