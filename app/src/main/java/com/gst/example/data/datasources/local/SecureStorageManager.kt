package com.gst.example.data.datasources.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import com.gst.example.SecureStorageConfig
import com.gst.common.sharedpreference.KeyExistPreferenceDelegate
import com.gst.common.sharedpreference.StringPreferenceDelegate
import com.gst.common.sharedpreference.removeData
import javax.inject.Inject

interface ISecureStorageManager {
  var sessionId: String

  var userId: String

  val hasSessionId: Boolean

  fun clearSessionId()

  fun clearAll()
}

class SecureStorageManager @Inject constructor(
  @ApplicationContext context: Context
) : ISecureStorageManager {

  companion object {
    private const val SESSION_ID_KEY = "SESSION_ID_KEY"

    private const val USER_ID_KEY = "USER_ID_KEY"
  }

  private val masterKey: MasterKey =
    MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
      .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
      .build()

  private val encryptedPrefs: SharedPreferences =
    EncryptedSharedPreferences.create(
      context,
      SecureStorageConfig.SECURE_SHARED_PREFERENCES_FILE_NAME,
      masterKey, // masterKey created above
      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

  override var sessionId: String by StringPreferenceDelegate(encryptedPrefs, SESSION_ID_KEY)

  override var userId: String by StringPreferenceDelegate(encryptedPrefs, USER_ID_KEY)

  override val hasSessionId: Boolean by KeyExistPreferenceDelegate(encryptedPrefs, SESSION_ID_KEY)

  override fun clearSessionId() = encryptedPrefs.removeData(SESSION_ID_KEY)

  override fun clearAll() {
    encryptedPrefs.edit {
      clear()
    }
  }
}