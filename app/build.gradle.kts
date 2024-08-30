plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("kotlin-kapt")
  id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
  id("com.google.dagger.hilt.android")
  id("dagger.hilt.android.plugin")

}

android {
  namespace = "com.app.sielseapplecturaskotlin"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.app.sielseapplecturaskotlin"
    minSdk = 23
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
  //dagger-hilt
  implementation("com.google.dagger:hilt-android:2.51.1")
  kapt("com.google.dagger:hilt-android-compiler:2.51.1")
  // Retrofit
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  //http
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
  //navigation
  val nav_version = "2.7.7"
  implementation("androidx.navigation:navigation-compose:$nav_version")

  val lifecycle_version = "2.8.4"
  val arch_version = "2.2.0"

  // ViewModel
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
  // ViewModel utilities for Compose
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")

  implementation("androidx.compose.runtime:runtime-livedata:1.6.8")
  //implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
  // LiveData
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
  // Lifecycles only (without ViewModel or LiveData)
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

  //Room
  implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
  //kapt("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
  implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")
  kapt("androidx.room:room-compiler:${rootProject.extra["room_version"]}")

  //coil - imagenens
  implementation("io.coil-kt:coil-compose:2.7.0")

  //mapbox
  implementation("com.mapbox.maps:android:11.4.1")
  implementation("com.mapbox.extension:maps-compose:11.4.1")

  //ubication
  implementation("com.google.android.gms:play-services-location:21.3.0")
  implementation("com.google.android.gms:play-services-maps:18.1.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

}