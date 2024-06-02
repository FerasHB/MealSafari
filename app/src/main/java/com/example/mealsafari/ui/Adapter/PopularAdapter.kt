package com.example.mealsafari.ui.Adapter

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.PopularItemsBinding
import syntax.com.playground.data.model.meal.Meal



class PopularAdapter(val meals: List<Meal>, val viewModel: ViewModel) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {



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

        holder.binding.imgPopularMeal.load(mealPopular.image)

        holder.binding.root.setOnClickListener {

            val bundle = Bundle()
            bundle.putSerializable("detail", mealPopular)
            holder.itemView.findNavController()
                .navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            Log.e(ContentValues.TAG, "Error loading Data from API Search: ")

           viewModel.setMeal(mealPopular)
            viewModel.getMealById(mealPopular.idMeal)


        }


    }


}