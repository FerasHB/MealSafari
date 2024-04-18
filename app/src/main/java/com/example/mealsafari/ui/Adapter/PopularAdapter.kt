package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.databinding.PopularItemsBinding
import com.example.mealsafari.ui.Data.MealPopular

class PopularAdapter(val viewModel: MealViewModel, val mealPopular: List<MealPopular>) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {


    inner class PopularViewHolder(val binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularItemsBinding.inflate(LayoutInflater.from(parent.context))
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mealPopular.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val mealPopular = mealPopular[position]
        viewModel.loadPopularMeal(mealPopular.mealImage)
        holder.binding.imgPopularMeal.load(mealPopular.mealName)

    }
}