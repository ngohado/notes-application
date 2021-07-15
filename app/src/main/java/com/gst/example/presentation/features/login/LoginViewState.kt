package com.gst.example.presentation.features.login

data class LoginViewState(
  val information: String,
  val userId: String,
  val loading: Boolean,
)