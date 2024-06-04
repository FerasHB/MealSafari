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
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FragmentDetailBinding
import syntax.com.playground.data.model.meal.Meal

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: ViewModel

    // Diese Methode wird aufgerufen, um die View für dieses Fragment zu erstellen.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding für das Fragment erstellen
        binding = FragmentDetailBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)

        // LiveData für Mahldetails beobachten und die Ansichten aktualisieren
        /*viewModel.mealDetails.observe(viewLifecycleOwner, Observer { meal ->
            meal?.let {
                binding.collapsingToolbar.title = it.name
                binding.tvCategoryInfo.text = it.category
                binding.tvCategoryArea.text = it.area
                binding.tvInstructions.text = it.instruction
                binding.imgMealDetail.load(it.image)
                // Optional: Lade das Video oder andere Details
                binding.imgYoutube.setOnClickListener {
                    val intent = Intent(ACTION_VIEW, Uri.parse(viewModel.meals.value!!.video))

                    // Starten des Intents
                    startActivity(intent)
                }
            }
        })*/

        // Rückgabe der aufgeblasenen Ansicht
        return binding.root
    }

    // Diese Methode wird aufgerufen, sobald die View für dieses Fragment erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mealId = arguments?.getLong("mealId") ?: 0L

        viewModel.getMealById(mealId)



        // Beobachten es LiveData-Objekts randomMeal aus dem ViewModel
        viewModel.meals.observe(viewLifecycleOwner) { mealObj: Meal ->
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
        }

        // OnClickListener für das YouTube-Icon, um das Video zu öffnen
        binding.imgYoutube.setOnClickListener {
            // Erstellen eines Intent, um das YouTube-Video zu öffnen
            val intent = Intent(ACTION_VIEW, Uri.parse(viewModel.meals.value!!.video))

            // Starten des Intents
            startActivity(intent)
        }

        // OnClickListener für die Schaltfläche "Speichern", um das Mahlzeitobjekt zu den Favoriten hinzuzufügen
        binding.btnSave.setOnClickListener {
            saveMeal(viewModel.meals.value!!)
            // Aktualisieren des Bilds der Speichern-Schaltfläche und Anzeige einer Toast-Nachricht
            binding.btnSave.setImageResource(R.drawable.ic_saved)
            Toast.makeText(requireContext(), "Meal saved", Toast.LENGTH_SHORT).show()
        }
    }

    // Methode zum Speichern der Mahlzeit in den Favoriten
    private fun saveMeal(meal: Meal) {
        viewModel.addToFavorites(meal)
    }
}
