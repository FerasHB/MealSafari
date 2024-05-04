package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.databinding.FavoriteItemBinding
import com.example.mealsafari.databinding.PopularItemsBinding
import syntax.com.playground.data.model.meal.Meal

class FavoriteAdapter(val meals: List<Meal>, val viewModel: MealViewModel) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {


    inner class FavoriteViewHolder(val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val meals = meals[position]

        holder.binding.imgFavMeal.load(meals.image)
        holder.binding.tvFavMealName.text = meals.name
    }
}