package com.gst.example.data.datasources.remote.api

import com.gst.example.data.datasources.remote.entities.LoginRequest
import com.gst.example.data.datasources.remote.entities.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
  @POST(ApiConstants.LOGIN_PATH)
  fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}