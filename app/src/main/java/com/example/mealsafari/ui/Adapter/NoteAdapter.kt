package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsafari.ViewModel
import com.example.mealsafari.databinding.SoppingListItemBinding
import com.example.mealsafari.ui.Data.Note
import com.example.mealsafari.ui.fragment.soppingList.ShoppingFragmentDirections

/**
 * Ein RecyclerView-Adapter für die Notizen.
 * @param note Die Liste der Notizen, die angezeigt werden sollen.
 * @param viewModel Ein ViewModel für den Zugriff auf die Daten.
 */
class NoteAdapter(val note: List<Note>, val viewModel: ViewModel) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    /**
     * Eine innere ViewHolder-Klasse für die Notizansicht.
     * @param binding Die Datenbindung für die Notizansicht.
     */
    inner class NoteViewHolder(val binding: SoppingListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * Erstellt und initialisiert eine ViewHolder-Instanz.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            SoppingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    /**
     * Gibt die Anzahl der Elemente in der Liste zurück.
     */
    override fun getItemCount(): Int {
        return note.size
    }

    /**
     * Bindet die Daten an die Ansichtselemente in der ViewHolder-Instanz.
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = note[position]

        // Die ID der aktuellen Notiz abrufen
        val noteId: Long = currentNote.id

        // Die Daten der aktuellen Notiz an die Ansichtselemente binden
        holder.binding.noteTitle.text = currentNote.noteTitle
        holder.binding.noteDesc.text = currentNote.noteDesc

        // Klicken auf die Notizansicht, um die Bearbeitungsansicht aufzurufen
        holder.binding.cvNote.setOnClickListener {
            // Zur Bearbeitungsansicht navigieren und die ID der Notiz übergeben
            holder.itemView.findNavController()
                .navigate(ShoppingFragmentDirections.actionShoppingFragmentToEditeFragment().setNoteId(noteId))
        }
    }
}
