package com.gst.example.domain.model

data class CheckBox(
    val id: String,
    val isDone: Boolean,
    val name: String,
    val priority: Int,
    val createAt: Long
)
