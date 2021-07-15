package com.gst.example

object NetworkConfig {
  const val CALL_TIMEOUT: Long = 40_000

  const val CONNECT_TIMEOUT: Long = 40_000

  const val READ_TIMEOUT: Long = 40_000

  const val WRITE_TIMEOUT: Long = 40_000

  const val API_DOMAIN_DEFAULT = "http://127.0.0.1:8000/"
}

object SecureStorageConfig {
  const val SECURE_SHARED_PREFERENCES_FILE_NAME = "GST_SECURE_FILE"
}