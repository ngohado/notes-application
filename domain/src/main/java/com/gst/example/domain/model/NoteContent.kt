package com.gst.example.domain.model

data class NoteContent(
    val id: String,
    var contentText: String,
    val images: MutableList<NoteImage>,
    val checkBoxes: MutableList<CheckBox>
)
