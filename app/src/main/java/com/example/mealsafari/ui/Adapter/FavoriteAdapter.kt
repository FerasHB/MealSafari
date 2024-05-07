package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FavoriteItemBinding
import com.example.mealsafari.databinding.PopularItemsBinding
import syntax.com.playground.data.model.meal.Meal

class FavoriteAdapter(val meals: List<Meal>, val viewModel: MealViewModel) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var favoriteMeals: List<Meal> = ArrayList()
    private lateinit var onFavoriteClickListener: OnFavoriteClickListener

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
        holder.binding.apply {
            tvFavMealName.text = favoriteMeals[position].name
            Glide.with(holder.itemView)
                .load(favoriteMeals[position].image)
                .error(R.drawable.backrounde)
                .into(imgFavMeal)
        }
        holder.itemView.setOnClickListener {
            onFavoriteClickListener.onFavoriteClick(favoriteMeals[position])
        }
    }

    fun getMelaByPosition(position: Int): Meal {
        return favoriteMeals[position]
    }

    fun setOnFavoriteMealClickListener(onFavoriteClickListener: OnFavoriteClickListener) {
        this.onFavoriteClickListener = onFavoriteClickListener
    }

    fun setFavoriteMealsList(favoriteMeals: List<Meal>) {
        this.favoriteMeals = favoriteMeals
        notifyDataSetChanged()
    }

    interface OnFavoriteClickListener {
        fun onFavoriteClick(meal: Meal)
    }


}