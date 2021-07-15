package com.gst.example.data.datasources.remote.entities

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val loginId: String? = null,
    val password: String? = null,
)