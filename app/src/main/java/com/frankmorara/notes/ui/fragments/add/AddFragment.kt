package com.frankmorara.notes.ui.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.frankmorara.notes.R
import com.frankmorara.notes.model.Note
import com.frankmorara.notes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add, container, false)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.button_add.setOnClickListener {
            addNotesToDatabase()
        }
        return view
    }

    private  fun addNotesToDatabase() {
        val title = text_title.text.toString()
        val content = text_content.text.toString()

        if (inputCheck(title, content)){
            val note = Note(0,title, content)
            noteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Added $title to notes",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, content: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
}