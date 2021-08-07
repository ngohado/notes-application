package com.gst.example.domain.repository

import com.gst.example.domain.model.Note

interface NoteRepository {

    fun getNoteById(accountId: String, noteId: String): Note

    fun getAllNote(accountId: String): List<Note>

    fun getNotesByTagId(accountId: String, tagId: String): List<Note>

    fun searchNote(key: String): List<Note>

    fun saveNote(note: Note): Boolean

    fun deleteNote(noteId: Long): Boolean
}