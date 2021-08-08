package com.gst.example.domain.model

data class Tag(
    val id: String,
    val name: String,
    val total: Long,
    val owner: Account,
    val createTime: Long,
    val updateTime: Long
)