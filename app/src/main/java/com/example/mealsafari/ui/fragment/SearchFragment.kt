package com.example.mealsafari.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.databinding.SearchFragmentBinding
import com.example.mealsafari.ui.Adapter.SearchAdapter

class SearchFragment : Fragment() {
    private lateinit var binding: SearchFragmentBinding
    private val viewModel: MealViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = SearchFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvResults.setHasFixedSize(true)
        binding.rvResults.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


        binding.ivArrowSearch.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.results.observe(
            viewLifecycleOwner
        ) { results ->
            if (results.isEmpty()) {
                // Keine Ergebnisse gefunden, RecyclerView ausblenden
                binding.rvResults.visibility = View.GONE
            } else {
                // Ergebnisse gefunden, RecyclerView anzeigen
                binding.rvResults.visibility = View.VISIBLE

                // Alphabetisch sortierte Ergebnisse anzeigen
                val sortedResults = results.sortedBy { it.name }
                binding.rvResults.adapter = SearchAdapter(sortedResults, viewModel)
            }
        }


        viewModel.inputText.observe(
            viewLifecycleOwner
        ) {
            viewModel.loadData(it)
        }

        viewModel.results.observe(
            viewLifecycleOwner
        ) {
            binding.rvResults.adapter = SearchAdapter(it, viewModel)
        }

        binding.edSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.updateInputText(p0.toString())
            }
        })



    }

}