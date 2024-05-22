package com.example.mealsafari.ui.fragment.soppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealsafari.ViewModel
import com.example.mealsafari.databinding.FragmentShoppingBinding
import com.example.mealsafari.ui.Adapter.NoteAdapter

/**
 * Ein Fragment-Unterklasse zum Anzeigen der Einkaufsliste.
 */
class ShoppingFragment : Fragment() {
    private lateinit var binding: FragmentShoppingBinding
    private val viewModel: ViewModel by activityViewModels()

    /**
     * Erstellt die Ansicht für dieses Fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(inflater)
        return binding.root
    }

    /**
     * Initialisiert die UI-Komponenten und richtet die Listener ein.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Stellt sicher, dass der RecyclerView eine feste Größe für die Leistung hat
        binding.homeRecyclerView.hasFixedSize()

        // Beobachtet Änderungen in der Liste der Notizen und aktualisiert den RecyclerView entsprechend
        viewModel.getAllNotes.observe(viewLifecycleOwner) { notizen ->
            binding.homeRecyclerView.adapter = NoteAdapter(notizen, viewModel)
        }

        // Navigiert zum AddFragment, wenn auf den FAB geklickt wird
        binding.addNoteFab.setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddFragment()
            )
        }
    }
}
