package com.frankmorara.notes.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.frankmorara.notes.R
import com.frankmorara.notes.model.Note
import com.frankmorara.notes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    private val args: UpdateFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        view.text_update_title.setText(args.currentNote.title)
        view.text_update_content.setText(args.currentNote.content)
        view.button_update.setOnClickListener {
            updateNote()
        }
        setHasOptionsMenu(true)
        return view
    }
    private fun updateNote(){
        val title = text_update_title.text.toString()
        val content = text_update_content.text.toString()

        if (inputCheck(title, content)){
            val updatedNote = Note(args.currentNote.id, title, content)
            noteViewModel.updateNote(updatedNote)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Successfully Updated $title", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_icon){
            deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun inputCheck(title: String, content: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.menu.delete_menu){
////            deleteNote()
//            Toast.makeText(requireContext(),"${args.currentNote.title}",Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                noteViewModel.deleteNote(args.currentNote)
                Toast.makeText(
                    requireContext(),
                    "Successfully removed ${args.currentNote.title}",
                    Toast.LENGTH_SHORT
                )
                    .show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            builder.setNegativeButton("No") { _, _ ->

            }
            builder.setTitle("Delete ${args.currentNote.title}?")
            builder
                .setMessage("Are you sure you want to delete ${args.currentNote.title}?")
            builder.create().show()
        }

}