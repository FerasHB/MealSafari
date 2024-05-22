package com.example.mealsafari.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mealsafari.ViewModel
import com.example.mealsafari.databinding.FavoriteFragmentBinding
import com.example.mealsafari.room.DataDao
import com.example.mealsafari.ui.Adapter.FavoriteAdapter

class FavoriteFragment:Fragment() {
    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var favoriteDataDao: DataDao
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



        viewModel.favoriteMeals.observe(viewLifecycleOwner) { favoriteMeals ->


           binding.favRecView.adapter = FavoriteAdapter(favoriteMeals,viewModel)




        }
    }

}