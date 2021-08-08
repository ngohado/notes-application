package com.gst.example.presentation.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import com.gst.example.R
import com.gst.common.views.SafetyClickListener
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*

abstract class BaseDialogFragment : DialogFragment(), IBaseFragment {
  override val baseActivity: BaseActivity?
    get() = activity as? BaseActivity

  override val safetyClick: SafetyClickListener by lazy { SafetyClickListener() }

  @CallSuper
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val rootView = inflater.inflate(R.layout.fragment_base, container, false)
    inflater.inflate(getLayoutId(), rootView.contentContainer, true)

    return rootView
  }

  @CallSuper
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initData(arguments)
    initViews()
    initActions()
    initObservers()
  }

  override fun onStart() {
    super.onStart()
    dialog?.apply {
      window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
      )

      window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
  }

  override fun showLoading() {
    loadingView.visibility = View.VISIBLE
  }

  override fun hideLoading() {
    loadingView.visibility = View.INVISIBLE
  }

  @CallSuper
  override fun onDestroyView() {
    safetyClick.cleanListeners()
    super.onDestroyView()
  }
}