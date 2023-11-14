package com.example.s_app5.screan

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.s_app5.R
import com.example.s_app5.component.NotInputText
import com.example.s_app5.component.NoteButton
import com.example.s_app5.data.NotesDataSource
import com.example.s_app5.module.Note
import java.nio.file.WatchEvent
import java.time.format.DateTimeFormatter

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScrean(
    notes:List<Note>,
    onAddNote:(Note) -> Unit,
    onRemoveNote:(Note) -> Unit,

){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current

    Column (modifier = Modifier.padding(6.dp)){
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name))
        }, actions ={ Icon(imageVector = Icons.Rounded.Notifications,
            contentDescription = "Icon")},
            //colors = MaterialTheme.colorScheme.onBackground
        )
    }
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 64.dp, bottom = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        NotInputText(
           modifier = Modifier.padding(top = 9.dp, bottom = 8.dp) ,
            text = title, label = "Title", onTextChange = {
                if (it.all { Char ->
                        Char.isLetter() || Char.isWhitespace()
                    }) title=it
            })
        NotInputText(
            modifier = Modifier.padding(top = 9.dp, bottom = 18.dp) ,
            text = description, label = "Add a Note", onTextChange = {
                if (it.all { Char ->
                        Char.isLetter() || Char.isWhitespace()
                    }) description=it

            })
NoteButton(
    modifier = Modifier.padding(top = 9.dp, bottom = 8.dp) ,
    text ="Save",
    onClick = {
        if (title.isNotEmpty() && description.isNotEmpty())
            onAddNote(Note(title = title, description = description))
            title=""
        description=""
        Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()

    })
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){note ->
NoteRow(note = note, onNoteClicked = {
    onRemoveNote(note)

})
            }
        }



    }

}
@Composable
fun NoteRow(
    modifier:Modifier=Modifier,
    note:Note,
    onNoteClicked:(Note) -> Unit
){
    Surface (
        modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
        color = Color(0xFF7B8892)
        ){
        Column (modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 6.dp)
            .clickable {
                       onNoteClicked(note)
            },
            horizontalAlignment = Alignment.Start){
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.titleMedium)
            Text(text = note.entryData.format(DateTimeFormatter.ofPattern("EEE ,d MMM")),
                style = MaterialTheme.typography.bodyLarge)

        }

    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreanPreview(){
    NoteScrean(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}