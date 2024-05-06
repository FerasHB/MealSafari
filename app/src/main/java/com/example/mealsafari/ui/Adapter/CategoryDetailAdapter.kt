package com.example.mealsafari.ui.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
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


        holder.binding.imgCategory.load(meal.image)
        holder.binding.tvCategoryBtmsheetName.text = meal.name


        val bundle = Bundle()
        bundle.putSerializable("detail", meal)
        holder.binding.root.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_categoryDetailFragment_to_detailFragment3)
            viewModel.setMeal(meal)
        }


    }


}
