package com.gst.example.build

object Dependencies {
  object Android {
    const val plugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    const val material = "com.google.android.material:material:${Versions.Google.material}"
  }

  object Kotlin {
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
    const val plugin =
      "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlin}"
  }

  object Coroutines {
    const val core =
      "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    const val android =
      "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
  }


  const val constraintLayout =
    "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
  const val recyclerView =
    "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

  object Core {
    const val ktx = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val legacySupportV4 =
      "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}"
  }

  object Activity {
    const val ktx = "androidx.activity:activity-ktx:${Versions.activity}"
    const val compose = "androidx.activity:activity-compose:${Versions.activity}"
  }

  object Lifecycle {
    const val runtime =
      "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle.lifecycle}"
    const val compiler =
      "androidx.lifecycle:lifecycle-compiler:${Versions.Lifecycle.lifecycle}"
    const val viewModelKtx =
      "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Lifecycle.lifecycle}"
    const val liveDataKtx =
      "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Lifecycle.lifecycle}"
    const val viewModelCompose =
      "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Lifecycle.compose}"
  }

  object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
  }

  object Navigation {
    const val plugin =
      "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Navigation.navigation}"
    const val safeArgsGradlePlugin =
      "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Navigation.navigation}"
    const val uiKtx =
      "androidx.navigation:navigation-ui-ktx:${Versions.Navigation.navigation}"
    const val fragmentKtx =
      "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation.navigation}"
  }

  object Security {
    const val crypto =
      "androidx.security:security-crypto:${Versions.Security.crypto}"
  }

  object Test {
    const val archCore =
      "androidx.arch.core:core-testing:${Versions.Test.archCore}"
  }

  object Compose {
    object Runtime {
      const val liveData =
        "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
      const val rxJava =
        "androidx.compose.runtime:runtime-rxjava2:${Versions.compose}"
    }

    object Foundation {
      const val foundation =
        "androidx.compose.foundation:foundation:${Versions.compose}"
    }

    object UI {
      const val ui = "androidx.compose.ui:ui:${Versions.compose}"
      const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }

    object Material {
      const val material =
        "androidx.compose.material:material:${Versions.compose}"
      const val iconsCore =
        "androidx.compose.material:material-icons-core:${Versions.compose}"
      const val iconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    }

    object Navigation {
      const val navigation =
        "androidx.navigation:navigation-compose:${Versions.Navigation.compose}"
    }
  }

  object PowerMock {
    const val core = "org.powermock:powermock-core:${Versions.powermock}"
    const val junit = "org.powermock:powermock-module-junit4:${Versions.powermock}"
    const val mockito = "org.powermock:powermock-api-mockito2:${Versions.powermock}"
  }

  object Hilt {
    const val plugin =
      "com.google.dagger:hilt-android-gradle-plugin:${Versions.Google.hilt}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.Google.hilt}"
    const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.Google.hilt}"
  }

  object Squareup {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Square.retrofit}"
    const val serializationConverter =
      "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.Square.serializationConverter}"
    const val loggingInterceptor =
      "com.squareup.okhttp3:logging-interceptor:${Versions.Square.okHttp}"
    const val adapterRxjava =
      "com.squareup.retrofit2:adapter-rxjava3:${Versions.Square.retrofit}"
    const val serializationJson =
      "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Square.serializationJson}"
  }

  object ReactiveX {
    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.ReactiveX.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.ReactiveX.rxAndroid}"
  }
}