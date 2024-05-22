package com.example.mealsafari.ui.fragment.soppingList

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.FragmentEditeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditeFragment : Fragment() {
    private lateinit var binding: FragmentEditeBinding
    private val viewModel: ViewModel by activityViewModels()
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

        val note = viewModel.getAllNotes.value?.find { it.id == noteId } ?: return
        binding.addNoteTitle.setText(note.noteTitle)
        binding.addNoteDesc.setText(note.noteDesc)

        binding.editNoteFab.setOnClickListener {
            note.noteTitle = binding.addNoteTitle.text.toString()
            note.noteDesc = binding.addNoteDesc.text.toString()

            // Zurück zum ShoppingFragment navigieren
            findNavController().navigate(R.id.action_editeFragment_to_shoppingFragment)
            Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()

            // Die aktualisierte Notiz speichern
            viewModel.updateNote(note)
        }

        binding.ivDelete.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.apply {
                setTitle("Confirm Deletion")
                setMessage("Are you sure you want to delete this note?")
                setPositiveButton("Ja") { _, _ ->
                    // Wenn der Benutzer "Ja" auswählt, lösche das Element
                    viewModel.deleteNote(noteId)
                    Toast.makeText(requireContext(), "Note deleted", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(EditeFragmentDirections.actionEditeFragmentToShoppingFragment())
                }
                setNegativeButton("Cancel") { dialog, _ ->
                    // Wenn der Benutzer "Abbrechen" auswählt, schließe den Dialog, ohne etwas zu tun
                    dialog.dismiss()
                }
                create().show()
            }
        }

    }
}