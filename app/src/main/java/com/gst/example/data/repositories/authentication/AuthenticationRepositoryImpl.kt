package com.gst.example.data.repositories.authentication

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import com.gst.example.data.datasources.local.ISecureStorageManager
import com.gst.example.data.models.exceptions.LoginIdInvalid
import com.gst.example.data.models.exceptions.NotFoundSessionError
import com.gst.example.data.models.exceptions.PasswordInvalid
import com.gst.example.data.datasources.memory.IMemoryDataStorage
import com.gst.example.data.datasources.remote.IAuthenticationRemoteDataSource
import com.gst.example.data.datasources.remote.entities.LoginRequest
import com.gst.example.data.models.dto.Session
import java.util.*
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationRemoteDataSource: IAuthenticationRemoteDataSource,
    private val secureStorageManager: ISecureStorageManager,
    private val memoryDataStorage: IMemoryDataStorage
) : AuthenticationRepository {

  override fun login(
    loginId: String,
    password: String,
    autoLoginEnable: Boolean
  ): Completable {
    return Completable.defer {
      // Validate loginId before perform login
      if (!autoLoginEnable && !validateLoginId(loginId)) return@defer Completable.error(
        LoginIdInvalid(loginId)
      )

      // Validate password before perform login
      if (!autoLoginEnable && !validatePassword(password)) return@defer Completable.error(
        PasswordInvalid(password)
      )

      val loginRequest = LoginRequest(loginId, password)
      return@defer authenticationRemoteDataSource.login(loginRequest)
        .flatMapCompletable { response ->
          storeSession(permanent = autoLoginEnable, session = Session(response.sessionId))
        }
    }
  }

  override fun logout(): Completable {
    return authenticationRemoteDataSource.logout()
      .doFinally {
        clearUserData()
      }
  }

  private fun clearUserData() {
    memoryDataStorage.clear()
    secureStorageManager.clearAll()
  }

  override fun storeSession(permanent: Boolean, session: Session): Completable {
    memoryDataStorage.setUserInfo(memoryDataStorage.getUserInfo().copy(sessionId = session.sessionId))
    return if (permanent) {
      Completable.fromAction {
        secureStorageManager.sessionId = session.sessionId
      }
    } else {
      Completable.complete()
    }
  }

  override fun getSession(): Single<Session> {
    val memorySessionId = memoryDataStorage.getSessionId()
    if (memorySessionId.isNotBlank()) {
      return Single.just(Session(memorySessionId))
    }

    return Single.defer {
      val sessionId = secureStorageManager.sessionId
      if (sessionId.isEmpty()) {
        return@defer Single.error(NotFoundSessionError())
      }
      return@defer Single.just(Session(sessionId))
    }
  }

  private fun validateLoginId(loginId: String): Boolean {
    return loginId.isNotEmpty()
  }

  private fun validatePassword(password: String): Boolean {
    return password.isNotEmpty()
  }
}