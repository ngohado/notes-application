package com.gst.example.presentation.features.login

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.gst.example.R
import dagger.hilt.android.AndroidEntryPoint
import com.gst.example.common.views.hideKeyBoard
import com.gst.example.common.views.requestFocusAndHideKeyBoard
import com.gst.example.presentation.base.BaseFragment
import com.gst.example.presentation.features.login.errors.LoginFailed
import kotlinx.android.synthetic.main.screen_login.*


@AndroidEntryPoint
class LoginScreen : BaseFragment(), OnTouchListener, View.OnKeyListener {

  // Use Hilt to inject and create instance of LoginView
  private val viewModel: LoginViewModel by viewModels()

  override fun getLayoutId(): Int = R.layout.screen_login

  override fun initData(data: Bundle?) {

  }

  override fun initViews() {
    txtAgreement.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    edtId.requestFocusAndHideKeyBoard()
  }

  override fun initActions() {
    safetyClick.setViewClickSafetyListener(btnLogin) {
      viewModel.login(
        loginId = edtId.text.toString(),
        password = edtPassword.text.toString(),
        autoLoginEnable = cbAutoLogin.isChecked
      )
    }

    safetyClick.setViewClickSafetyListener(ivInfo) {
      Toast.makeText(
        context,
        getString(R.string.all_not_support_feature_message),
        Toast.LENGTH_SHORT
      )
        .show()
    }

    safetyClick.setViewClickSafetyListener(txtAgreement) {
      Toast.makeText(
        context,
        getString(R.string.all_not_support_feature_message),
        Toast.LENGTH_SHORT
      )
        .show()
    }

    rootLayout.setOnTouchListener(this)
    edtPassword.setOnKeyListener(this)
    edtId.setOnKeyListener(this)
  }

  override fun initObservers() {
    viewModel.store.observe(
      owner = this,
      selector = { state -> state.loading },
      observer = { loading ->
        if (loading) showLoading() else hideLoading()
      }
    )

    viewModel.store.observe(
      owner = this,
      selector = { state -> state.information },
      observer = { information ->
        txtInformationArea.text = fromHtml(information)
      }
    )

    viewModel.store.observeAnyway(
      owner = this,
      selector = { state -> state.userId },
      observer = { userId ->
        edtId?.setText(userId)
        edtPassword?.setText("")
      }
    )

    // Handle errors
    viewModel.errorLiveEvent.observe(this) { error ->
      when (error) {
        is LoginFailed -> {
          // TODO: Handle server error
          showSingleOptionDialog(
            getString(R.string.login_dialog_error_title_input_incorrect),
            getString(R.string.login_dialog_error_message_input_incorrect),
            getString(R.string.all_button_close_text)
          )
        }

        else -> {
          // Do nothing
        }
      }
    }

    viewModel.loginSuccessLiveEvent.observe(this) {

    }
  }

  @SuppressLint("ClickableViewAccessibility") // We don't support accessibility.
  override fun onTouch(view: View?, p1: MotionEvent?): Boolean {
    when (view) {
      !is EditText -> {
        view?.hideKeyBoard()
      }
    }
    return true
  }

  override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {
    if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_UP) {
      view?.hideKeyBoard()
      return true
    }
    return false
  }
}