package com.example.mealsafari.ui.fragment.soppingList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FragmentEditeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditeFragment : Fragment() {
    private lateinit var binding: FragmentEditeBinding
    private val viewModel: MealViewModel by activityViewModels()
    private var noteId: Long = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteId = requireArguments().getLong("noteId")

        val note = viewModel.getAllNotes.value?.find { it.id == noteId }?:return

        binding.addNoteTitle.setText(note.noteTitle)
        binding.addNoteDesc.setText(note.noteDesc)

        binding.editNoteFab.setOnClickListener {
            note.noteTitle = binding.addNoteTitle.text.toString()
            note.noteDesc = binding.addNoteDesc.text.toString()


                viewModel.updateNote(note)
            findNavController().navigate(R.id.action_editeFragment_to_shoppingFragment)

        }

        binding.ivDelete.setOnClickListener {
            viewModel.deleteNote(noteId)
            findNavController().navigate(EditeFragmentDirections.actionEditeFragmentToShoppingFragment())        }


    }
}