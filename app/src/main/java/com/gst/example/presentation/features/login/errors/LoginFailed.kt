package com.gst.example.presentation.features.login.errors

data class LoginFailed(
  val specificError: Throwable
) : Exception(specificError.message)