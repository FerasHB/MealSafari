package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.databinding.MealsByCategoryBinding
import syntax.com.playground.data.model.meal.Meal


class CategoryDetailAdapter(private val mealList: List<Meal>, val viewModel: MealViewModel) :
    RecyclerView.Adapter<CategoryDetailAdapter.MealByCatViewHolder>() {


    inner class MealByCatViewHolder(val binding: MealsByCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealByCatViewHolder {
        val binding = MealsByCategoryBinding.inflate(LayoutInflater.from(parent.context))
        return MealByCatViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealByCatViewHolder, position: Int) {
        val meal = mealList[position]
        holder.binding.tvCategoryBtmsheetName.text = meal.name
        holder.binding.imgCategory.load(meal.image)


    }
}