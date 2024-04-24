package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.CategoryMealsItemBinding
import com.example.mealsafari.ui.Data.Category


class MealsByCategoryAdapter(
    private val mealCategory: List<Category>,
    val viewModel: MealViewModel
) :
    RecyclerView.Adapter<MealsByCategoryAdapter.MealsCategoryViewHolder>() {

    inner class MealsCategoryViewHolder(val binding: CategoryMealsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsCategoryViewHolder {
        val binding = CategoryMealsItemBinding.inflate(LayoutInflater.from(parent.context))
        return MealsCategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        // kann sein das nicht funktuniert
        return mealCategory.size
    }

    override fun onBindViewHolder(holder: MealsCategoryViewHolder, position: Int) {
        val categoryMeals = mealCategory[position]

        holder.binding.imgMeal.load(categoryMeals.categoryImage)
        holder.binding.tvMealName.text = categoryMeals.categoryName

        holder.binding.root.setOnClickListener {
            // viewModel.loadMealByCategory(categoryMeals.categoryName)
            holder.itemView.findNavController().navigate(R.id.categoryDetailFragment)
        }
    }
}