package com.example.s_app5.screan

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.s_app5.data.NotesDataSource
import com.example.s_app5.module.Note

class NoteViewModel(): ViewModel() {

    var noteList= mutableStateListOf<Note>()
    init {
        noteList.addAll(NotesDataSource().loadNotes())

    }

    fun addNote(note: Note){
        noteList.add(note)
    }
    fun removeNote(note: Note){
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note> {
        return noteList

    }

}