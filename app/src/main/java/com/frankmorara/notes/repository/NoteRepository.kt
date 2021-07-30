package com.frankmorara.notes.repository

import androidx.lifecycle.LiveData
import com.frankmorara.notes.model.Note
import com.frankmorara.notes.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val readAllNotes: LiveData<List<Note>> = noteDao.readAllNotes()

      fun addNote(note: Note){
        noteDao.addNote(note)
    }
    fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
     fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
  fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }
}