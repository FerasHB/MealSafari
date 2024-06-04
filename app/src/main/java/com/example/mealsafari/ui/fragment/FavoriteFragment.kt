package com.example.mealsafari.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsafari.ViewModel
import com.example.mealsafari.databinding.FavoriteFragmentBinding
import com.example.mealsafari.room.DataDao
import com.example.mealsafari.ui.Adapter.CategoryResultsAdapter
import com.example.mealsafari.ui.Adapter.FavoriteAdapter
import syntax.com.playground.data.model.meal.Meal

class FavoriteFragment : Fragment() {
    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var recView:RecyclerView
    val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteFragmentBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Beobachten des LiveData-Objekts allMeals aus dem ViewModel
        viewModel.allMeals.observe(viewLifecycleOwner) { favoriteMeals ->

            // Aktualisieren des RecyclerView-Adapters mit den Favoriten-Mahlzeiten
            binding.favRecView.adapter = FavoriteAdapter(favoriteMeals, viewModel)
        }
    }

}