package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.CategoryMealsItemBinding
import com.example.mealsafari.ui.Data.Category

/**
 * Adapter für die Liste der Mahlzeiten in einer bestimmten Kategorie.
 * @param mealCategory Eine Liste von Kategorienobjekten, die Mahlzeiten darstellen.
 * @param viewModel Das zugehörige ViewModel für die Kommunikation mit der UI.
 */
class MealsByCategoryAdapter(
    private val mealCategory: List<Category>,
    val viewModel: ViewModel
) :
    RecyclerView.Adapter<MealsByCategoryAdapter.MealsCategoryViewHolder>() {

    // ViewHolder für jedes Mahlzeiten-Kategorie-Element
    inner class MealsCategoryViewHolder(val binding: CategoryMealsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Erstellen eines ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsCategoryViewHolder {
        val binding = CategoryMealsItemBinding.inflate(LayoutInflater.from(parent.context))
        return MealsCategoryViewHolder(binding)
    }

    // Anzahl der Elemente in der Liste
    override fun getItemCount(): Int {
        return mealCategory.size
    }

    // Binden der Daten an die View des ViewHolders
    override fun onBindViewHolder(holder: MealsCategoryViewHolder, position: Int) {
        val categoryMeals = mealCategory[position]

        // Laden des Bilds für die Mahlzeit und Setzen des Namens
        holder.binding.imgMeal.load(categoryMeals.categoryImage)
        holder.binding.tvMealName.text = categoryMeals.categoryName

        // Klick-Listener für das Mahlzeiten-Kategorie-Element
        holder.binding.root.setOnClickListener {
            // Aufrufen der Funktion im ViewModel, um Mahlzeiten nach Kategorie zu laden
            viewModel.loadMealByCategory(categoryMeals.categoryName)
            // Navigation zum Detailbildschirm für die Kategorie
            holder.itemView.findNavController().navigate(R.id.categoryDetailFragment)
        }
    }
}
