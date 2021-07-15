package com.gst.example.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import com.gst.example.common.reactive.SingleLiveEvent
import com.gst.example.common.reactive.addToCompositeDisposable

abstract class BaseViewModel<S : Any> : ViewModel() {
  val store by lazy {
    ViewStateStore(this.initState())
  }

  val currentState: S
    get() = store.state

  private val _errorLiveEvent: SingleLiveEvent<Throwable> = SingleLiveEvent()
  val errorLiveEvent: LiveData<Throwable>
    get() = _errorLiveEvent

  private val compositeDisposable = CompositeDisposable()

  abstract fun initState(): S

  override fun onCleared() {
    compositeDisposable.clear()
    super.onCleared()
  }

  fun Disposable.addToCompositeDisposable() {
    addToCompositeDisposable(compositeDisposable)
  }

  protected fun dispatchError(error: Throwable) {
    _errorLiveEvent.value = error
  }

  protected fun dispatchState(state: S) {
    store.dispatchState(state = state)
  }
}