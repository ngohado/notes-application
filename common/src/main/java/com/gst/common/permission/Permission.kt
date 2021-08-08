package com.gst.common.permission

data class Permission constructor(
  val permission: String,
  val granted: Boolean,
  val preventAskAgain: Boolean
)
