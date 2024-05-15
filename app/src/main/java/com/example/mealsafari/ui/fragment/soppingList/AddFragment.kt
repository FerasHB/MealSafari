package com.example.mealsafari.ui.fragment.soppingList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FragmentAddBinding
import com.example.mealsafari.databinding.FragmentEditeBinding
import com.example.mealsafari.databinding.FragmentShoppingBinding
import com.example.mealsafari.ui.Data.Note


/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val viewModel: MealViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSaveShoppingItem.setOnClickListener {
            binding.apply {
                val note = Note(
                    noteDesc = addNoteDesc.text.toString(),
                    noteTitle = addNoteTitle.text.toString()

                )
                Toast.makeText(requireContext(), "Note Saved", Toast.LENGTH_SHORT).show()

                viewModel.saveNote(note)

            }
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToShoppingFragment())
        }
    }

}