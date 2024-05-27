package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FavoriteItemBinding
import syntax.com.playground.data.model.meal.Meal

class FavoriteAdapter( var favoriteMeals: List<Meal>,
                       private val viewModel: ViewModel,
                       private val onFavoriteClickListener: OnFavoriteClickListener) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {


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
        holder.binding.apply {
            tvFavMealName.text = meal.name
            Glide.with(holder.itemView)
                .load(meal.image)

                .into(imgFavMeal)
            root.setOnClickListener {
                onFavoriteClickListener.onFavoriteClick(meal)
            }
        }
    }



    interface OnFavoriteClickListener {
        fun onFavoriteClick(meal: Meal)
    }


}