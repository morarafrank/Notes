package com.frankmorara.notes.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.frankmorara.notes.R
import com.frankmorara.notes.model.Note
import com.frankmorara.notes.ui.fragments.list.ListFragmentDirections
import kotlinx.android.synthetic.main.single_item_view.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var notesList = emptyList<Note>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater
           .from(parent.context)
           .inflate(R.layout.single_item_view,
               parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notesList[position]
        holder.itemView.apply {
            textview_title.text = currentItem.title
            textview_content.text = currentItem.content
            single_note.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
        
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(note: List<Note>) {
        this.notesList = note
        notifyDataSetChanged()
    }
}
