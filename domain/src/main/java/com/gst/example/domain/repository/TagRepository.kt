package com.gst.example.domain.repository

import com.gst.example.domain.model.Tag

interface TagRepository {

    fun getAllTags(accountId: Long): List<Tag>

    fun saveTag(tag: Tag): Boolean

    fun deleteTag(tagId: Long): Boolean
}