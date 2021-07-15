package com.gst.example.data.models.exceptions

import com.gst.example.data.datasources.remote.entities.ServerResponseCode

sealed class ServerError(code: String?) : Exception("$code")

class LoginError : ServerError(ServerResponseCode.LOGIN_ERROR.code)

class LoginLockError : ServerError(ServerResponseCode.LOGIN_LOCK_ERROR.code)

class SystemError : ServerError(ServerResponseCode.SYSTEM_ERROR.code)

class IllegalParameterError : ServerError(ServerResponseCode.ILLEGAL_PARAMETER.code)

class SessionTimeoutError : ServerError(ServerResponseCode.SESSION_TIMEOUT.code)

class ServerUnknownError(code: String?) : ServerError(code)

// TODO Continue define error class

fun String?.toServerError(): ServerError {
  return ServerResponseCode.values()
    .find { serverResponseCode -> serverResponseCode.code == this }
    ?.toServerError() ?: ServerUnknownError(this)
}

fun String?.isStatusSuccess(): Boolean {
  return this == ServerResponseCode.OK.code
}

fun ServerResponseCode?.toServerError(): ServerError? {
  return when (this ?: return null) {
    ServerResponseCode.OK -> ServerUnknownError(code)
    ServerResponseCode.SYSTEM_ERROR -> SystemError()
    ServerResponseCode.ILLEGAL_PARAMETER -> IllegalParameterError()
    ServerResponseCode.LOGIN_ERROR -> LoginError()
    ServerResponseCode.LOGIN_LOCK_ERROR -> LoginLockError()
    ServerResponseCode.SESSION_TIMEOUT -> SessionTimeoutError()
    ServerResponseCode.BULK_RUNNING -> TODO()
    ServerResponseCode.ONLINE_CLOSE -> TODO()
    ServerResponseCode.ILLEGAL_VERSION -> TODO()
    ServerResponseCode.FILE_BROKEN -> TODO()
    ServerResponseCode.VIRUS_CHECK -> TODO()
    ServerResponseCode.NOT_EXIST_PROPERTY_ERROR -> TODO()
    ServerResponseCode.ILLEGAL_RESPONSE_ERROR -> TODO()
    ServerResponseCode.ILLEGAL_STATUS_CD_ERROR -> TODO()
  }
}