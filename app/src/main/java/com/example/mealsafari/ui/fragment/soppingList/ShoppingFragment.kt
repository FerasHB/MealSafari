package com.example.mealsafari.ui.fragment.soppingList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.databinding.FragmentShoppingBinding
import com.example.mealsafari.ui.Adapter.NoteAdapter

class ShoppingFragment : Fragment() {
    private lateinit var binding: FragmentShoppingBinding

    private val viewModel: MealViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShoppingBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecyclerView.hasFixedSize()
        viewModel.getAllNotes.observe(viewLifecycleOwner) {
            binding.homeRecyclerView.adapter = NoteAdapter(it,viewModel)
        }

        binding.addNoteFab.setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddFragment()
            )
        }
    }


}