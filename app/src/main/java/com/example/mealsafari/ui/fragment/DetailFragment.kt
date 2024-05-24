package com.example.mealsafari.ui.fragment

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FragmentDetailBinding
import syntax.com.playground.data.model.meal.Meal

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    val viewModel: ViewModel by activityViewModels()





    // Diese Methode wird aufgerufen, um die View für dieses Fragment zu erstellen.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding für das Fragment erstellen
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    // Diese Methode wird aufgerufen, sobald die View für dieses Fragment erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       //viewModel.getMEalByIDFromApi(mealId.idMeal.toString())
        viewModel.loadData("")

        // Beobachten Sie das LiveData-Objekt randomMeal aus dem ViewModel
        viewModel.randomMeal.observe(viewLifecycleOwner) { mealObj: Meal ->
            // Laden des Bildes in das ImageView
            binding.imgMealDetail.load(mealObj.image)

            // Setzen der Texte für die verschiedenen TextViews
           binding.tvCategoryArea.text = mealObj.area
            binding.tvCategoryInfo.text = mealObj.category
            binding.tvInstructions.text = mealObj.instruction

            // Setzen des Titels für das CollapsingToolbarLayout
            binding.collapsingToolbar.title = mealObj.name

            // Setzen der Textfarben für den CollapsingToolbarLayout
            binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
            binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.black))

            // OnClickListener für das ImageView, das das YouTube-Video starten soll
            binding.imgYoutube.setOnClickListener {
                // Erstellen eines Intent, um das YouTube-Video zu öffnen
                val intent = Intent(ACTION_VIEW, Uri.parse(mealObj.video))

                // Starten des Intents
                startActivity(intent)
            }
        }

        binding.btnSave.setOnClickListener {

            val meal = viewModel.favoriteMeals.value


            if (meal != null) {
                saveFavoriteMeal(meal)
            }

            // Optional: Feedback geben, dass das Element erfolgreich als Favorit gespeichert wurde
            Toast.makeText(requireContext(), "Meal Saved", Toast.LENGTH_SHORT).show()
        }

    }
    private fun saveFavoriteMeal(meal: List<Meal>) {
        viewModel.saveFavoriteMeal(meal)
    }



}
