package com.gst.example.data.datasources.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import com.gst.example.data.datasources.remote.api.DynamicApiService
import com.gst.example.data.datasources.remote.entities.LoginRequest
import com.gst.example.data.datasources.remote.entities.LoginResponse
import com.gst.example.data.models.exceptions.isStatusSuccess
import com.gst.example.data.models.exceptions.toServerError
import javax.inject.Inject

class AuthenticationRemoteDataSource @Inject constructor(
  private val apiService: DynamicApiService
) : IAuthenticationRemoteDataSource {
  override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
    return apiService.login(loginRequest)
      .flatMap { response ->
        if (response.statusCode.isStatusSuccess()) {
          Single.just(response)
        } else {
          Single.error(response.statusCode.toServerError())
        }
      }
  }

  override fun logout(): Completable {
    // TODO Implement logout
    return Completable.complete()
  }
}