package com.gst.example.domain.model

import java.util.Base64

data class NoteImage(
    val id: String,
    val image: Base64,
    val createTime: Long
)