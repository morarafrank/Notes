package com.frankmorara.notes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.frankmorara.notes.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
     fun readAllNotes() : LiveData<List<Note>>

     @Update
     fun updateNote(note: Note)

     @Delete
     fun deleteNote(note: Note)

     @Query("DELETE FROM note_table")
   fun deleteAllNotes()
}