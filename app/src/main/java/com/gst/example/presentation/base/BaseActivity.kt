package com.gst.example.presentation.base

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import com.gst.example.common.permission.Permission
import com.gst.example.common.permission.PermissionUtil

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(), DialogCommonView {
  private lateinit var permissionUtil: PermissionUtil

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(getLayoutId())
    // Init immediately after create activity
    permissionUtil = PermissionUtil(this)
  }

  /**
   * Method to get layout xml id (e.g., R.layout.screen_abc)
   *
   * @return a layout xml id.
   */
  abstract fun getLayoutId(): Int

  /**
   * Request grants permissions.
   *
   * @param permissions (variable number of arguments): List permission want to request
   * (e.g.,[Manifest.permission.ACCESS_COARSE_LOCATION], [Manifest.permission.READ_EXTERNAL_STORAGE],...)
   *
   * @param callback : Return result of request. There are two parameters:
   * areGrantedAll: True if all permissions are granted. False if at least one of those is declined.
   * deniedPermissions: List [Permission] request which are declined by user.
   */
  fun requestPermissions(
    vararg permissions: String,
    callback: (areGrantedAll: Boolean, deniedPermissions: List<Permission>) -> Unit
  ) {
    permissionUtil.request(*permissions, callback = callback)
  }

  override fun showSingleOptionDialog(
    title: String?,
    message: String,
    button: String,
    listener: DialogButtonClickListener?
  ): AlertDialog? {
    return buildDialog(title, message)
      .setPositiveButton(button) { dialog, _ ->
        listener?.invoke(dialog) ?: dialog.dismiss()
      }
      .show()
  }

  override fun showSingleOptionDialog(
    title: Int?,
    message: Int,
    button: Int,
    listener: DialogButtonClickListener?
  ): AlertDialog? {
    return showSingleOptionDialog(
      title = title?.let { getString(it) },
      message = getString(message),
      button = getString(button),
      listener = listener
    )
  }

  override fun showDoubleOptionsDialog(
      title: String?,
      message: String,
      firstButton: String,
      secondButton: String,
      firstButtonListener: DialogButtonClickListener?,
      secondButtonListener: DialogButtonClickListener?
  ): AlertDialog? {
    return buildDialog(title, message)
      .setPositiveButton(firstButton) { dialog, _ ->
        firstButtonListener?.invoke(dialog) ?: dialog.dismiss()
      }
      .setNegativeButton(secondButton) { dialog, _ ->
        secondButtonListener?.invoke(dialog) ?: dialog.dismiss()
      }
      .show()
  }

  override fun showDoubleOptionsDialog(
      title: Int?,
      message: Int,
      firstButton: Int,
      secondButton: Int,
      firstButtonListener: DialogButtonClickListener?,
      secondButtonListener: DialogButtonClickListener?
  ): AlertDialog? {
    return showDoubleOptionsDialog(
      title = title?.let { getString(it) },
      message = getString(message),
      firstButton = getString(firstButton),
      secondButton = getString(secondButton),
      firstButtonListener = firstButtonListener,
      secondButtonListener = secondButtonListener
    )
  }

  private fun buildDialog(title: String?, message: String): AlertDialog.Builder {
    return AlertDialog.Builder(this)
      .setCancelable(false)
      .setTitle(title)
      .setMessage(message)
  }
}