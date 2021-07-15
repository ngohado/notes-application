package com.gst.example.data.repositories.authentication

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import com.gst.example.data.models.dto.Session

interface AuthenticationRepository {
  fun login(
    loginId: String,
    password: String,
    autoLoginEnable: Boolean
  ): Completable

  fun logout(): Completable

  fun storeSession(permanent: Boolean, session: Session): Completable

  fun getSession(): Single<Session>
}