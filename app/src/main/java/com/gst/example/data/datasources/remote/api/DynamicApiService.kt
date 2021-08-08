package com.gst.example.data.datasources.remote.api

import io.reactivex.rxjava3.core.Single
import com.gst.example.NetworkConfig
import com.gst.common.logging.DebugLog
import com.gst.example.data.datasources.remote.entities.LoginRequest
import com.gst.example.data.datasources.remote.entities.LoginResponse
import retrofit2.Retrofit
import javax.inject.Inject

class DynamicApiService @Inject constructor(
  private val retrofitBuilder: Retrofit.Builder
) : ApiService {
  private var dynamicApiService: ApiService? = null

  private var url: String = NetworkConfig.API_DOMAIN_DEFAULT
    set(value) {
      if (field != value) {
        DebugLog.d("Update api domain. Old url: $field, New url: $value")
        field = value
        dynamicApiService = null
      } else {
        DebugLog.d("Api domain update is same. Url: $value")
      }
    }

  private val apiService: ApiService
    get() {
      if (dynamicApiService == null) {
        dynamicApiService = retrofitBuilder
          .baseUrl(url)
          .build()
          .create(ApiService::class.java)
      }

      return dynamicApiService!!
    }

  fun updateApiDomain(url: String) {
    this.url = url
  }

  override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
    return apiService.login(loginRequest)
  }
}