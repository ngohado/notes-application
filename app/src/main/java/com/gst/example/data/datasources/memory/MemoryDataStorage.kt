package com.gst.example.data.datasources.memory

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import com.gst.example.data.models.dto.UserInfo
import javax.inject.Inject

interface IMemoryDataStorage {
  fun getUserInfo(): UserInfo

  fun getUserInfoSubject(): Observable<UserInfo>

  fun setUserInfo(userInfo: UserInfo)

  fun getSessionId(): String

  fun setSessionId(session: String)

  fun getServiceId(): String

  fun setServiceId(service: String)

  fun clear()
}

class MemoryDataStorage @Inject constructor() : IMemoryDataStorage {
  companion object {
    const val NO_VALUE_STRING = ""
  }

  private val _userInfoSubject = BehaviorSubject.createDefault(UserInfo())

  // region Session
  override fun getSessionId(): String {
    return getUserInfo().sessionId
  }

  override fun setSessionId(session: String) {
    setUserInfo(getUserInfo().copy(sessionId = session))
  }
  // endregion

  // region Service id
  override fun getServiceId(): String {
    return getUserInfo().serviceId
  }

  override fun setServiceId(service: String) {
    setUserInfo(getUserInfo().copy(serviceId = service))
  }
  // endregion

  // region User info
  override fun getUserInfo(): UserInfo {
    return _userInfoSubject.value
  }

  override fun getUserInfoSubject(): Observable<UserInfo> {
    return _userInfoSubject
  }

  override fun setUserInfo(userInfo: UserInfo) {
    _userInfoSubject.onNext(userInfo)
  }
  // endregion

  override fun clear() {
    _userInfoSubject.onNext(UserInfo())
  }
}