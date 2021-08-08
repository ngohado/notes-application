package com.gst.common.views

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun EditText.requestFocusAndHideKeyBoard() {
  this.requestFocus()
  hideKeyBoard()
}

fun View.toVisible() {
  this.visibility = View.VISIBLE
}

fun View.toGone() {
  this.visibility = View.GONE
}

fun View.toInvisible() {
  this.visibility = View.INVISIBLE
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
  this.addTextChangedListener(object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
      afterTextChanged.invoke(editable.toString())
    }
  })
}