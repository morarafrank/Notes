package com.frankmorara.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.frankmorara.notes.model.Note
import com.frankmorara.notes.data.NoteDatabase
import com.frankmorara.notes.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
   val readAllNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        readAllNotes = repository.readAllNotes
    }

    fun addNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.addNote(note)
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateNote(note)
        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteNote(note)
        }
    }
     fun deleteAllNotes(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllNotes()
        }
    }

}