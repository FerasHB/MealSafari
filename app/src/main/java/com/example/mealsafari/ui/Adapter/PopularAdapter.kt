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
import com.example.mealsafari.ui.Data.PopularMeal
import com.example.mealsafari.ui.fragment.HomeFragmentDirections
import syntax.com.playground.data.model.meal.Meal

/**
 * Adapter für die Liste der beliebten Mahlzeiten.
 * @param meals Eine Liste von Mahlzeitenobjekten, die als beliebt markiert sind.
 * @param viewModel Das zugehörige ViewModel für die Kommunikation mit der UI.
 */
class PopularAdapter(val meals: List<PopularMeal>, val viewModel: ViewModel) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    // ViewHolder für jedes beliebte Mahlzeitenelement
    inner class PopularViewHolder(val binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Erstellen eines ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularItemsBinding.inflate(LayoutInflater.from(parent.context))
        return PopularViewHolder(binding)
    }

    // Anzahl der Elemente in der Liste
    override fun getItemCount(): Int {
        return meals.size
    }

    // Binden der Daten an die View des ViewHolders
    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val mealPopular = meals[position]



        // Laden des Bilds für die beliebte Mahlzeit
        holder.binding.imgPopularMeal.load(mealPopular.strMealThumb)

        // Klick-Listener für das beliebte Mahlzeitenelement
        holder.binding.root.setOnClickListener {
            // Erstellen eines Bundle-Objekts und Hinzufügen der Mahlzeitendaten
           // val bundle = Bundle()
           // bundle.putSerializable("detail", mealPopular)
            // Navigation zum Detailbildschirm für die Mahlzeit
            holder.itemView.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(mealPopular.idMeal))
            // Loggen eines Fehlers, wenn Daten nicht geladen werden können (kann entfernt werden, wenn nicht benötigt)
            Log.e(ContentValues.TAG, "Error loading Data from API Search: ")

        }
    }
}
