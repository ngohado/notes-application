package com.gst.example.presentation.base

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.gst.example.R
import dagger.hilt.android.AndroidEntryPoint
import com.gst.common.views.SafetyClickListener
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*

@AndroidEntryPoint
abstract class BaseFragment : Fragment(), IBaseFragment {

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

  override fun showLoading() {
    loadingView.visibility = View.VISIBLE
  }

  override fun hideLoading() {
    loadingView.visibility = View.INVISIBLE
  }

  fun fromHtml(source: String?): Spanned? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    } else {
      Html.fromHtml(source)
    }
  }

  @CallSuper
  override fun onDestroyView() {
    safetyClick.cleanListeners()
    super.onDestroyView()
  }
}