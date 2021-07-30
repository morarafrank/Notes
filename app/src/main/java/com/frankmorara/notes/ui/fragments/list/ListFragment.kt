package com.frankmorara.notes.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.frankmorara.notes.R
import com.frankmorara.notes.ui.adapter.ListAdapter
import com.frankmorara.notes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment: Fragment(R.layout.fragment_list) {

    private lateinit var noteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

//        Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.readAllNotes.observe(viewLifecycleOwner, { note ->
            adapter.setData(note)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_icon){
            deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            noteViewModel.deleteAllNotes()
            Toast.makeText(
                requireContext(),
                "Successfully removed Everthing",
                Toast.LENGTH_SHORT
            )
                .show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete Everything?")
        builder
            .setMessage("Are you sure you want to delete Everything")
        builder.create().show()
    }
}