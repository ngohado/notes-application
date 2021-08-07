package com.gst.example.presentation.features.login

import com.gst.common.logging.DebugLog
import com.gst.common.reactive.ListenTriggerLiveEvent
import com.gst.common.reactive.TriggerLiveEvent
import com.gst.common.reactive.applyIO
import com.gst.example.data.repositories.authentication.AuthenticationRepository
import com.gst.example.presentation.base.BaseViewModel
import com.gst.example.presentation.features.login.errors.LoginFailed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) : BaseViewModel<LoginViewState>() {

  private val _loginSuccessLiveEvent: TriggerLiveEvent = TriggerLiveEvent()
  val loginSuccessLiveEvent: ListenTriggerLiveEvent
    get() = _loginSuccessLiveEvent

  override fun initState(): LoginViewState = LoginViewState(information = "", userId = "", loading = false)

  fun login(loginId: String, password: String, autoLoginEnable: Boolean) {
    store.dispatchState(state = store.state.copy(loading = true))

    authenticationRepository.login(loginId, password, autoLoginEnable)
      .applyIO()
      .subscribe({
        store.dispatchState(state = store.state.copy(loading = false))
        _loginSuccessLiveEvent.trigger()
      }, { error ->
        DebugLog.e("Login  Error: $error")
        store.dispatchState(state = store.state.copy(loading = false))
        dispatchError(LoginFailed(error))
      })
      .addToCompositeDisposable()
  }
}