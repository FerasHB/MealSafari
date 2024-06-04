package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FavoriteItemBinding
import syntax.com.playground.data.model.meal.Meal

/**
 * Adapter für die Liste der Favoriten.
 * @param favoriteMeals Eine Liste von Favoriten-Mahlzeitenobjekten.
 * @param viewModel Das zugehörige ViewModel für die Kommunikation mit der UI.
 */
class FavoriteAdapter(private var favoriteMeals: List<Meal>, var viewModel: ViewModel) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    // ViewHolder für jedes Favoriten-Mahlzeitenelement
    inner class FavoriteViewHolder(val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Erstellen eines ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteViewHolder(binding)
    }

    // Anzahl der Elemente in der Liste
    override fun getItemCount(): Int {
        return favoriteMeals.size
    }

    // Binden der Daten an die View des ViewHolders
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val meal = favoriteMeals[position]

        // Setzen des Namens und Ladens des Bilds für die Favoriten-Mahlzeit
        holder.binding.tvFavMealName.text = meal.name
        holder.binding.imgFavMeal.load(meal.image)

        // Klick-Listener für das Favoriten-Mahlzeitenelement
        holder.binding.root.setOnClickListener {
            // Navigation zum Detailbildschirm für die Mahlzeit
            holder.itemView.findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment)
            // Übergeben der Mahlzeitendaten an das ViewModel
            viewModel.setMeal(meal)
        }

        // Klick-Listener für das Löschen-Symbol
        holder.binding.ivDelete.setOnClickListener {
            // Entfernen der Mahlzeit aus den Favoriten
            viewModel.removeFromFavorites(meal)
            // Anzeige einer Toast-Nachricht zur Bestätigung der Entfernung
            Toast.makeText(holder.itemView.context, "${meal.name} removed from favorites", Toast.LENGTH_SHORT).show()
        }
    }
}
