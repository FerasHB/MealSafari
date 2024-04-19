package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.R
import com.example.mealsafari.databinding.PopularItemsBinding
import com.example.mealsafari.ui.Data.MealPopular
import syntax.com.playground.data.model.meal.Meal

class PopularAdapter( val meals: List<MealPopular>) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
//    private lateinit var onItemClick: OnItemClick
//    private var mealsList: List<MealPopular> = ArrayList()


    inner class PopularViewHolder(val binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularItemsBinding.inflate(LayoutInflater.from(parent.context))
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val mealPopular = meals[position]
        holder.binding.apply {

        }

        holder.binding.imgPopularMeal.load(mealPopular.mealImage)
       holder.binding.root.setOnClickListener {
            holder.binding.imgPopularMeal.findNavController().navigate(R.id.detailFragment)
        }

        /*holder.itemView.setOnClickListener {
            onItemClick.onItemClick(mealsList[position])
        }*/
    }

    interface OnItemClick{
        fun onItemClick(meal: MealPopular)
    }
}