package com.gst.example.presentation.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import com.gst.example.presentation.features.MainActivity
import java.util.*

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
  private val viewModel: SplashViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.onSplashLaunched()

    viewModel.splashJobsCompleteLiveEvent.observe(this) { loggedIn ->
      // Close splash screen then open to main screen.
      val intent = Intent(this@SplashActivity, MainActivity::class.java)
        .putExtra(MainActivity.LOGGED_IN_KEY, loggedIn)
      startActivity(intent)
      finish()
    }
  }
}

