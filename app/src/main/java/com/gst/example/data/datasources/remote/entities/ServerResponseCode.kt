package com.gst.example.data.datasources.remote.entities

enum class ServerResponseCode(val code: String) {
  // region: Server error
  OK("S200"),
  SYSTEM_ERROR("S500"),
  ILLEGAL_PARAMETER("S400"),
  LOGIN_ERROR("S401"),
  LOGIN_LOCK_ERROR("S405"),
  SESSION_TIMEOUT("S408"),
  BULK_RUNNING("S409"),
  ONLINE_CLOSE("S410"),
  ILLEGAL_VERSION("S420"),
  FILE_BROKEN("S421"),
  VIRUS_CHECK("S422"),
  NOT_EXIST_PROPERTY_ERROR("S430"),
  ILLEGAL_RESPONSE_ERROR("S400"),
  ILLEGAL_STATUS_CD_ERROR("S900"),
  // endregion
}