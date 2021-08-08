package com.gst.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.gst.common.logging.DebugLog

object NetworkUtils {

  fun getConnectionType(context: Context?): NetworkingType {
    if (context == null) {
      DebugLog.e("Context is null")
      return NetworkingType.NONE
    }

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
        return when {
          hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkingType.WIFI

          hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkingType.CELLULAR

          else -> NetworkingType.NONE
        }
      }
    } else {
      connectivityManager?.activeNetworkInfo?.run {
        return when (type) {
          ConnectivityManager.TYPE_WIFI -> NetworkingType.WIFI

          ConnectivityManager.TYPE_MOBILE -> NetworkingType.CELLULAR

          else -> NetworkingType.NONE
        }
      }
    }
    return NetworkingType.NONE
  }
}

enum class NetworkingType {
  WIFI,
  CELLULAR,
  NONE
}