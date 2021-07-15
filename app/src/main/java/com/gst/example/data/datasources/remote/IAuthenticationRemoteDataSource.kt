package com.gst.example.data.datasources.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import com.gst.example.data.datasources.remote.entities.LoginRequest
import com.gst.example.data.datasources.remote.entities.LoginResponse

interface IAuthenticationRemoteDataSource {
  fun login(loginRequest: LoginRequest): Single<LoginResponse>

  fun logout(): Completable
}