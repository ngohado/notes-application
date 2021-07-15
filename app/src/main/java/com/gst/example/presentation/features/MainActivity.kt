package com.gst.example.presentation.features

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.gst.example.R
import dagger.hilt.android.AndroidEntryPoint
import com.gst.example.presentation.base.BaseActivity


@AndroidEntryPoint
class MainActivity : BaseActivity() {

  companion object {
    const val LOGGED_IN_KEY = "LOGGED_IN_KEY"
  }

  override fun getLayoutId(): Int = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initData()
  }

  private fun initData() {
    val userHasLoggedIn = intent.extras?.getBoolean(LOGGED_IN_KEY, false) ?: false

    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
    val graph = navHostFragment?.navController?.navInflater?.inflate(R.navigation.nav_graph) ?: return
    graph.startDestination = if (userHasLoggedIn) {
      R.id.topScreen
    } else {
      R.id.loginScreen
    }

    navHostFragment.navController.graph = graph
  }
}