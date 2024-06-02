package com.example.mealsafari.ui.fragment


import android.os.Bundle
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


    private lateinit var binding: HomeFragmentBinding
    private val viewModel: ViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadRandomMeal()
        viewModel.loadAllMealCategories()
        viewModel.loadPopularMeal("Dessert")



        viewModel.meals.observe(viewLifecycleOwner) { mealObj: Meal ->
            binding.imgRandomMeal.load(mealObj.image)
        }
        binding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }

        binding.randomMealCard.setOnClickListener {
            findNavController().navigate(R.id.detailFragment)
        }

        viewModel.popularMeal.observe(viewLifecycleOwner) { popularMeals ->

            binding.recViewMealPopular.adapter = PopularAdapter(popularMeals, viewModel)
        }

        viewModel.getMealsByCategory.observe(viewLifecycleOwner) {

            binding.recViewCategories.adapter = CategoryHomeAdapter(it, viewModel)
        }

        binding.recViewCategories.setOnClickListener {
            findNavController().navigate(R.id.categoryMealsFragment)
        }


    }


}