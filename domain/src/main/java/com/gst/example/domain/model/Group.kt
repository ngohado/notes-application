package com.gst.example.domain.model

data class Group(
    val id: String,
    val name: String,
    val owner: Account,
    val members: MutableList<Account>,
    val createTime: Long,
    val updateTime: Long
)
