package com.example.s_app5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.s_app5.module.Note
import com.example.s_app5.screan.NoteScrean
import com.example.s_app5.screan.NoteViewModel
import com.example.s_app5.ui.theme.SApp5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SApp5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val noteViewModel :NoteViewModel by viewModels()
                    NotesApp(noteViewModel = NoteViewModel())
                }
            }
        }
    }
}
@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    val noteList= noteViewModel.getAllNotes()
    NoteScrean(notes = noteList,
        onAddNote = {
        noteViewModel.addNote(it)
    }, onRemoveNote = {
        noteViewModel.removeNote(it)
    })


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SApp5Theme {
    }
}