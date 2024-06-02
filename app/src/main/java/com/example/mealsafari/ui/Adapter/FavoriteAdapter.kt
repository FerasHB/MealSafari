package com.example.mealsafari.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FavoriteItemBinding
import syntax.com.playground.data.model.meal.Meal

class FavoriteAdapter( private var favoriteMeals: List<Meal>, var viewModel: ViewModel ) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    fun getMelaByPosition(position: Int):Meal{
        return favoriteMeals[position]
    }
    inner class FavoriteViewHolder(val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriteMeals.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val meal = favoriteMeals[position]

        holder.binding.tvFavMealName.text = meal.name
        holder.binding.imgFavMeal.load(meal.image)

        holder.binding.root.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment)
            viewModel.setMeal(meal)
        }

        holder.binding.ivDelete.setOnClickListener {
            viewModel.removeFromFavorites(meal)
            Toast.makeText(holder.itemView.context, "${meal.name} removed from favorites", Toast.LENGTH_SHORT).show()
        }
    }




}