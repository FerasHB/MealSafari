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
import com.example.mealsafari.databinding.ItemSearchResultBinding

import syntax.com.playground.data.model.meal.Meal

class SearchAdapter(
    private val dataset: List<Meal>, private val viewModel: ViewModel
) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val result = dataset[position]
        holder.binding.ivMela.load(result.image)
        holder.binding.tvMealName.text = result.name
        holder.binding.tvMealCategory.text = result.category
        holder.binding.tvArea.text = result.area

        holder.binding.root.setOnClickListener {


            val bundle = Bundle()
            bundle.putSerializable("detail", result)
            holder.itemView.findNavController()
                .navigate(R.id.action_searchFragment_to_detailFragment, bundle)
            Log.e(ContentValues.TAG, "Error loading Data from API Search: ")

            viewModel.setMeal(result)


        }

    }
}