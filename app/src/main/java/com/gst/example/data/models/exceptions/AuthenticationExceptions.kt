package com.gst.example.data.models.exceptions

sealed class AuthenticationError(message: String) : Exception(message)

class LoginIdInvalid(loginId: String) : AuthenticationError("LoginId entered = $loginId is invalid")

class PasswordInvalid(password: String) : AuthenticationError("Password entered = $password is invalid")

class NotFoundSessionError : AuthenticationError("Session id doesn't exist")
