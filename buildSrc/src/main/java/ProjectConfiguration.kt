import AndroidConfig.roomVersion

object AndroidConfig {
  const val kotlinVersion = "1.5.10"
  const val minSdk = 21
  const val compileSdk = 30
  const val buildTools = "30.0.2"
  const val roomVersion = "2.3.0-beta01"
  const val targetSdk = 30
}

object Dependencies {
  const val kotlinStandardLib = "org.jetbrains.kotlin:kotlin-stdlib:${AndroidConfig.kotlinVersion}"
  const val kotlinCoreKtx = "androidx.core:core-ktx:1.3.2"

  const val legacySupportV4 = "androidx.legacy:legacy-support-v4:1.0.0"

  const val uiEssentialAppCompat = "androidx.appcompat:appcompat:1.2.0"
  const val uiEssentialConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
  const val uiEssentialRecycleView = "androidx.recyclerview:recyclerview:1.1.0"

  private const val lifecycleVersion = "2.2.0"
  const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
  const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
  const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
  const val lifecycleAnnotation = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"

  const val uiMaterialSupport = "com.google.android.material:material:1.2.1"

  const val uiGlide = "com.github.bumptech.glide:glide:4.11.0"

  private const val navigationVersion = "2.3.1"
  const val uiNavigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
  const val uiNavigationUI = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

  private const val hiltAndroidVersion = "2.37"
  private const val hiltVersion = "1.0.0-alpha01"
  const val diHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidVersion"
  const val diHiltAndroid = "com.google.dagger:hilt-android:$hiltAndroidVersion"
  const val diHiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltAndroidVersion"
  const val diHiltLifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltVersion"
  const val diHiltCompiler = "androidx.hilt:hilt-compiler:$hiltVersion"

  private const val retrofitAndroidVersion = "2.9.0"
  private const val okhttpLoggingVersion = "4.9.0"
  const val networkRetrofit = "com.squareup.retrofit2:retrofit:$retrofitAndroidVersion"
  const val networkRetrofitJSONConvert =
    "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
  const val networkRetrofitLogging = "com.squareup.okhttp3:logging-interceptor:$okhttpLoggingVersion"
  const val adapterRxjava = "com.squareup.retrofit2:adapter-rxjava3:$retrofitAndroidVersion"
  const val jsonSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"

  const val androidxSecurityCrypto = "androidx.security:security-crypto:1.1.0-alpha03"

  const val reactiveRxJava3 = "io.reactivex.rxjava3:rxjava:3.0.7"
  const val reactiveRxAndroid = "io.reactivex.rxjava3:rxandroid:3.0.0"

  const val roomDatabase = "androidx.room:room-runtime:$roomVersion"
  const val roomKtxDatabase = "androidx.room:room-ktx:$roomVersion"
  const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
  const val roomRxJava = "androidx.room:room-rxjava3:$roomVersion"

  const val materialDesign = "com.google.android.material:material:1.3.0"

  const val testArchCore = "androidx.arch.core:core-testing:2.1.0"

  const val testPowerMockCore = "org.powermock:powermock-core:2.0.9"
  const val testPowerMockJunit = "org.powermock:powermock-module-junit4:2.0.9"
  const val testPowerMockMockito= "org.powermock:powermock-api-mockito2:2.0.9"

}