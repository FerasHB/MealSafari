package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.CategoryItemBinding
import com.example.mealsafari.ui.Data.Category

/**
 * Adapter für die Kategorienansicht auf der Startseite.
 * @param mealCategory Eine Liste von Kategorienobjekten.
 * @param viewModel Das zugehörige ViewModel für die Kommunikation mit der UI.
 */
class CategoryHomeAdapter(
    private val mealCategory: List<Category>,
    private val viewModel: ViewModel
) : RecyclerView.Adapter<CategoryHomeAdapter.CategoryViewHolder>() {

    // ViewHolder für jedes Kategorieelement
    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Erstellen eines ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryViewHolder(binding)
    }

    // Anzahl der Elemente in der Liste
    override fun getItemCount(): Int {
        return mealCategory.size
    }

    // Binden der Daten an die View des ViewHolders
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val mealCategory = mealCategory[position]

        // Laden des Bilds für die Kategorie und Setzen des Kategorienamens
        holder.binding.apply {
            imgCategory.load(mealCategory.categoryImage)
            tvCategoryName.text = mealCategory.categoryName
        }

        // Klick-Listener für das Kategorieelement
        holder.binding.root.setOnClickListener {
            // Aufrufen der Funktion im ViewModel, um Mahlzeiten nach Kategorie zu laden
            viewModel.loadMealByCategory(mealCategory.categoryName)
            // Navigation zum Detailbildschirm für die Kategorie
            holder.itemView.findNavController().navigate(R.id.categoryDetailFragment)
        }
    }
}
