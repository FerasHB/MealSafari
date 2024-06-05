package com.example.mealsafari.ui.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.MealsResultsItemBinding
import com.example.mealsafari.ui.fragment.CategoryResultsFragmentDirections
import syntax.com.playground.data.model.meal.Meal

/**
 * Adapter für die Liste der Mahlzeiten in einer bestimmten Kategorie.
 * @param mealList Eine Liste von Mahlzeitenobjekten.
 * @param viewModel Das zugehörige ViewModel für die Kommunikation mit der UI.
 */
class CategoryResultsAdapter(private val mealList: List<Meal>, val viewModel: ViewModel) :
    RecyclerView.Adapter<CategoryResultsAdapter.MealByCatViewHolder>() {

    // ViewHolder für jedes Mahlzeitenelement
    inner class MealByCatViewHolder(val binding: MealsResultsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Erstellen eines ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealByCatViewHolder {
        val binding = MealsResultsItemBinding.inflate(LayoutInflater.from(parent.context))
        return MealByCatViewHolder(binding)
    }

    // Anzahl der Elemente in der Liste
    override fun getItemCount(): Int {
        return mealList.size
    }

    // Binden der Daten an die View des ViewHolders
    override fun onBindViewHolder(holder: MealByCatViewHolder, position: Int) {
        val meal = mealList[position]

        // Laden des Bilds für die Mahlzeit und Setzen des Namens
        holder.binding.imgCategory.load(meal.image)
        holder.binding.tvCategoryBtmsheetName.text = meal.name



        // Klick-Listener für das Mahlzeitenelement
        holder.binding.root.setOnClickListener {
            // Navigation zum Detailbildschirm für die Mahlzeit
            holder.itemView.findNavController().navigate(CategoryResultsFragmentDirections.actionCategoryDetailFragmentToDetailFragment3().setMealId(meal.idMeal))
            // Übergeben der Mahlzeitendaten an das ViewModel
            // Laden der Mahlzeiten nach Kategorie
           // viewModel.loadMealByCategory("category")
           // viewModel.setMeal(meal)
        }
    }
}
