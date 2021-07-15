package com.gst.example.data.datasources.remote.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
  @SerialName("statusCd")
  val statusCode: String,
  @SerialName("sessionId")
  val sessionId: String,
)
