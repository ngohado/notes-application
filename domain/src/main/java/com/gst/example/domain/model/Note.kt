package com.gst.example.domain.model

data class Note(
    val id: String,
    val title: String,
    val createTime: Long,
    val updateTime: Long,
    val priority: Long,
    val content: NoteContent,
    val tags: MutableList<Tag>,
    val owner: Account,
    val sharedAccounts: MutableList<Account>,
    val remind: Long,
    val isRead: Boolean,
    val permission: String
)
