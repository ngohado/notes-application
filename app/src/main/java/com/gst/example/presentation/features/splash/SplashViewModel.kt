package com.gst.example.presentation.features.splash

import androidx.lifecycle.LiveData
import com.gst.example.common.logging.DebugLog
import com.gst.example.common.reactive.SingleLiveEvent
import com.gst.example.common.reactive.applyIO
import com.gst.example.data.repositories.authentication.AuthenticationRepository
import com.gst.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) : BaseViewModel<SplashViewState>() {

  companion object {
    private const val MIN_SPLASH_WAIT_TIME = 2000L
  }

  /**
   * The [Boolean] value to indicate that user has either logged in or not.
   */
  private val _splashJobsCompleteLiveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
  val splashJobsCompleteLiveEvent: LiveData<Boolean>
    get() = _splashJobsCompleteLiveEvent

  override fun initState(): SplashViewState = SplashViewState()

  fun onSplashLaunched() {
    authenticationRepository.getSession()
      .delaySubscription(MIN_SPLASH_WAIT_TIME, TimeUnit.MILLISECONDS)
      .applyIO()
      .subscribe({ session ->
        _splashJobsCompleteLiveEvent.value = session.sessionId.isNotBlank()
      }, { error ->
        DebugLog.e(error.toString())
        _splashJobsCompleteLiveEvent.value = false
      })
      .addToCompositeDisposable()
  }
}