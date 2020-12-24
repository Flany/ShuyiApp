package com.andorid.sylink.mes.plugin

object Versions {

    //
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion = 16
    const val targetSdkVersion = 30

    //
    const val kotlinVersion = "1.4.20"
    const val kotlinCoroutinesCore = "1.4.2"
    const val kotlinCoroutinesAndroid = "1.4.2"
    const val ktxCore = "1.3.2"
    const val appCompat = "1.2.0"
    const val androidMaterial = "1.2.1"
    const val constraintLayout = "2.0.4"
    const val junit = "4.+"
    const val junitExt = "1.1.2"
    const val espressoCore = "3.3.0"

    const val viewModel = "2.2.0"
    const val liveData = "2.2.0"
    const val vectorDrawable = "1.1.0"
    const val navigationFragment = "2.3.2"
    const val navigationUI = "2.3.2"

    //retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val loggingInterceptor = "3.9.0"

    //glide
    const val glide = "4.11.0"
    const val glideCompiler = "4.11.0"

    //loadSir
    const val loadSir = "1.3.8"

    //smartRefresh
    const val smartRefresh = "2.0.1"

    //glide
    const val hiltAndroid = "2.28-alpha"
    const val hiltAndroidCompiler = "2.28-alpha"
    const val hiltLifecycleViewModel = "1.0.0-alpha02"
    const val hiltLifecycleViewModelCompiler = "1.0.0-alpha02"

    //bugly
    const val bugly = "3.3.1"
}

object Ktx {

}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object Hilt {
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}"

    const val hiltLifecycleViewModel =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycleViewModel}"
    const val hiltLifecycleViewModelCompiler =
        "androidx.hilt:hilt-compiler:${Versions.hiltLifecycleViewModelCompiler}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
}

object LoadSir {
    const val loadSir = "com.kingja.loadsir:loadsir:${Versions.loadSir}"
}

object SmartRefresh {
    const val refreshKernel = "com.scwang.smart:refresh-layout-kernel:${Versions.smartRefresh}"
    const val refreshHeader = "com.scwang.smart:refresh-header-classics:${Versions.smartRefresh}"
    const val refreshFooter = "com.scwang.smart:refresh-footer-classics:${Versions.smartRefresh}"
}

object Bugly {
    const val bugly = "com.tencent.bugly:crashreport:${Versions.bugly}"
}

object BaseDependencies {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val kotlinCoroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutinesCore}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutinesAndroid}"

    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:${Versions.vectorDrawable}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUI}"
}

