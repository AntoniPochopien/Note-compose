package com.example.note.screens.noteScreen

import android.widget.Toast
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note.R
import com.example.note.models.Note
import com.example.note.screens.noteScreen.widgets.NoteButton
import com.example.note.screens.noteScreen.widgets.NoteInput
import com.example.note.screens.noteScreen.widgets.NoteRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
) {

    var title = remember {
        mutableStateOf("")
    }
    var description = remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.background,
                actionIconContentColor = MaterialTheme.colorScheme.background
            )
        )
    }) {
        val ctx = LocalContext.current
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInput(text = title.value, label = "Title", onTextChange = { v ->
                if (v.all { char -> char.isLetter() || char.isWhitespace() }) title.value = v
            })

            NoteInput(text = description.value, label = "Add a note", onTextChange = { v ->
                if (v.all { char -> char.isLetter() || char.isWhitespace() }) description.value = v
            })

            Spacer(modifier = Modifier.height(8.dp))

            NoteButton(text = "Save", onClick = {

                if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                    onAddNote(Note(title = title.value, description =  description.value))
                    title.value = ""
                    description.value = ""
                    Toast.makeText( ctx, "Note Added", Toast.LENGTH_SHORT).show()
                }
            })
            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn{
                items(notes){
                    note -> NoteRow(note = note, onNoteClicked = {onRemoveNote(note)})
                }
            }
        }
    }
}


