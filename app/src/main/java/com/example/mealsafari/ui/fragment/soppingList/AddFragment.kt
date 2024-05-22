package com.example.mealsafari.ui.fragment.soppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealsafari.ViewModel
import com.example.mealsafari.databinding.FragmentAddBinding
import com.example.mealsafari.ui.Data.Note

/**
 * Ein einfaches [Fragment]-Unterklasse.
 * Verwende die [AddFragment.newInstance] Factory-Methode, um
 * eine Instanz dieses Fragments zu erstellen.
 */
class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Das Layout für dieses Fragment aufblasen
        binding = FragmentAddBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Klicken auf "Save" zum Speichern der Einkaufsposition
        binding.tvSaveShoppingItem.setOnClickListener {
            binding.apply {
                // Die Eingaben des Benutzers abrufen und eine Notiz erstellen
                val note = Note(
                    noteDesc = addNoteDesc.text.toString(),
                    noteTitle = addNoteTitle.text.toString()
                )

                // Eine Toast-Nachricht anzeigen, um zu bestätigen, dass die Notiz gespeichert wurde
                Toast.makeText(requireContext(), "Note Saved", Toast.LENGTH_SHORT).show()

                // Die Notiz im ViewModel speichern
                viewModel.saveNote(note)
            }

            // Zurück zum ShoppingFragment navigieren
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToShoppingFragment())
        }
    }
}
