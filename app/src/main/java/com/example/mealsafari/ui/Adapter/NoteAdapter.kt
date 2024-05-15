package com.example.mealsafari.ui.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.SoppingListItemBinding
import com.example.mealsafari.ui.Data.Note
import com.example.mealsafari.ui.fragment.soppingList.ShoppingFragmentDirections

class NoteAdapter(val note: List<Note>, val viewModel: MealViewModel) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val binding: SoppingListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            SoppingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = note[position]
        val noteId: Long =currentNote.id
        holder.binding.noteTitle.text = currentNote.noteTitle
        holder.binding.noteDesc.text = currentNote.noteDesc

        holder.binding.cvNote.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(ShoppingFragmentDirections.actionShoppingFragmentToEditeFragment())
        }

       /* holder.binding.cvNote.setOnClickListener {


            val bundle = Bundle()
            bundle.putLong("noteId", currentNote.id)
            viewModel.setNote(note)
            holder.binding.cvNote.findNavController()
                .navigate(R.id.action_shoppingFragment_to_editeFragment)
            // Log.e(ContentValues.TAG, "Error loading Data from DataBase: ")


        }*/
    }
}