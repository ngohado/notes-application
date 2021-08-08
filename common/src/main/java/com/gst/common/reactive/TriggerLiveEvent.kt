package com.gst.common.reactive

class TriggerLiveEvent : ListenTriggerLiveEvent() {
  fun trigger() {
    value = true
  }
}

open class ListenTriggerLiveEvent : SingleLiveEvent<Boolean>(initValue = false) {
  fun wasTriggered(): Boolean {
    return value == true
  }
}
