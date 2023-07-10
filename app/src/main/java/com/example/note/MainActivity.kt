package com.example.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.collectAsState

import androidx.lifecycle.viewmodel.compose.viewModel


import com.example.note.screens.noteScreen.NoteScreen
import com.example.note.screens.noteScreen.NoteViewModel
import com.example.note.ui.theme.NoteTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteTheme {
                val noteViewModel = viewModel<NoteViewModel>()
                NoteScreen(
                    notes = noteViewModel.noteList.collectAsState().value,
                    onAddNote = { noteViewModel.addNote(it) },
                    onRemoveNote = { noteViewModel.removeNote(it) },
                )
            }
        }
    }
}

