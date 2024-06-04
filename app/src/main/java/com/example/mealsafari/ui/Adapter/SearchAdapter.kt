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

/**
 * Adapter für die Suchergebnisse.
 * @param dataset Eine Liste von Mahlzeitenobjekten, die die Suchergebnisse darstellen.
 * @param viewModel Das zugehörige ViewModel für die Kommunikation mit der UI.
 */
class SearchAdapter(
    private val dataset: List<Meal>, private val viewModel: ViewModel
) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {
    // ViewHolder für jedes Suchergebnis-Element
    inner class ItemViewHolder(val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Erstellen eines ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // Anzahl der Elemente in der Liste
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Binden der Daten an die View des ViewHolders
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val result = dataset[position]
        // Laden des Bilds für die Mahlzeit und Setzen des Namens, der Kategorie und des Bereichs
        holder.binding.ivMela.load(result.image)
        holder.binding.tvMealName.text = result.name
        holder.binding.tvMealCategory.text = result.category
        holder.binding.tvArea.text = result.area

        // Klick-Listener für das Suchergebnis-Element
        holder.binding.root.setOnClickListener {
            // Erstellen eines Bundle-Objekts und Hinzufügen der Mahlzeitendaten
            val bundle = Bundle()
            bundle.putSerializable("detail", result)
            // Navigation zum Detailbildschirm für die Mahlzeit
            holder.itemView.findNavController()
                .navigate(R.id.action_searchFragment_to_detailFragment, bundle)
            // Loggen eines Fehlers, wenn Daten nicht geladen werden können (kann entfernt werden, wenn nicht benötigt)
            Log.e(ContentValues.TAG, "Error loading Data from API Search: ")
            // Setzen der ausgewählten Mahlzeit im ViewModel
            viewModel.setMeal(result)
        }
    }
}
