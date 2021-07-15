package com.gst.example.data.datasources.remote.api

import com.gst.example.common.network.NetworkingType

object ApiConstants {
  const val LOGIN_PATH = "login/"

  private const val NETWORK_TYPE_NONE = "00"
  private const val NETWORK_TYPE_CELLULAR = "01"
  private const val NETWORK_TYPE_WIFI = "02"
  fun NetworkingType.toNetworkAPIValue(): String {
    return when (this) {
      NetworkingType.WIFI -> NETWORK_TYPE_WIFI
      NetworkingType.CELLULAR -> NETWORK_TYPE_CELLULAR
      NetworkingType.NONE -> NETWORK_TYPE_NONE
    }
  }
}