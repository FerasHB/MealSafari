package com.example.mealsafari.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mealsafari.ViewModel
import com.example.mealsafari.databinding.SearchFragmentBinding
import com.example.mealsafari.ui.Adapter.SearchAdapter

class SearchFragment : Fragment() {

    // Deklaration der Variablen
    private lateinit var binding: SearchFragmentBinding
    private val viewModel: ViewModel by activityViewModels()

    // Diese Methode wird aufgerufen, um die View für dieses Fragment zu erstellen.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Das Binding-Objekt für das Fragment erstellen
        binding = SearchFragmentBinding.inflate(inflater)
        return binding.root
    }

    // Diese Methode wird aufgerufen, sobald die View für dieses Fragment erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Festlegen von Eigenschaften des RecyclerViews
        binding.rvResults.setHasFixedSize(true)
        binding.rvResults.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        // Navigieren zurück, wenn auf den Zurück-Pfeil geklickt wird
        binding.ivArrowSearch.setOnClickListener {
            findNavController().navigateUp()
        }

        // Beobachten der Eingabe des Benutzers und Laden der entsprechenden Daten
        viewModel.inputText.observe(viewLifecycleOwner) { text ->
            viewModel.loadData(text)
        }

        // Beobachten der Suchergebnisse und Aktualisieren des RecyclerView-Adapters
        viewModel.results.observe(viewLifecycleOwner) { results ->
            if (results.isEmpty()) {
                // Wenn keine Ergebnisse vorhanden sind, RecyclerView ausblenden
                binding.rvResults.visibility = View.GONE
            } else {
                // Wenn Ergebnisse vorhanden sind, RecyclerView anzeigen
                binding.rvResults.visibility = View.VISIBLE

                // Ergebnisse alphabetisch sortieren und den RecyclerView-Adapter aktualisieren
                val sortedResults = results.sortedBy { it.name }
                binding.rvResults.adapter = SearchAdapter(sortedResults, viewModel)
            }
        }

        // Textänderungen im Suchfeld überwachen und ViewModel aktualisieren
        binding.edSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.updateInputText(p0.toString())
            }
        })
    }
}
