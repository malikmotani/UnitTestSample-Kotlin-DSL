object ApplicationId {
    val id = "com.unittestsample.app"
}

object Release {
    const val versionCode = 1
    const val versionName = "1.0"
    const val compileSdkVersion = 28
    const val targetSdkVersion = 28
    const val minSdkVersion = 21
}

object Config {
    const val gradle = "com.android.tools.build:gradle:3.4.2"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    const val navigationGradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigationVersion}"
}

object Version {
    // Kotlin based
    const val kotlinVersion = "1.3.41"
    const val kotlinCoreVersion = "1.0.2"

    //RxJava & RxAndroid
    const val rxkotlinVersion = "2.2.0"
    const val rxandroidVersion = "2.1.0"
    const val rxbindingVersion = "2.1.1"

    //json
    const val gsonVersion = "2.8.5"

    //Dagger
    const val daggerVersion = "2.18"

    //image
    const val glideVersion = "4.9.0"

    //Networking
    const val retrofitVersion = "2.4.0"
    const val okhttpLoggingVersion = "3.11.0"

    //Android jetpack
    const val appcompatVersion = "1.1.0-alpha05"
    const val constraintLayoutVersion = "2.0.0-alpha3"
    const val navigationVersion = "2.1.0"
    const val lifecycleVersion = "2.0.0"
    const val materialComponentVersion = "1.0.0"
    const val legacySupportVersion = "1.0.0"

    //test
    const val testRunnerVersion = "1.1.1"
    const val junitVersion = "4.12"

    //timber
    const val timber = "4.7.1"

    //multidex
    const val multidexVersion = "2.0.1"

    //circular imageview
    const val circularImageViewVersion = "3.0.0"

    //unit test
    const val mockitoVersion = "2.21.0"
    const val mockitoInlineVersion = "2.19.0"
    const val coreTestingVersion = "1.1.1"
    const val mockitoKotlinVersion = "2.1.0"
    const val daggerMockVersion = "0.8.5"
    const val kotlinTestVersion = "3.3.2"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlinVersion}"
}

object Log {
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"
}

object Support {
    const val core = "androidx.core:core-ktx:${Version.kotlinCoreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
    const val lifeCycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleVersion}"
    const val materialComponent =
        "com.google.android.material:material:${Version.materialComponentVersion}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Version.legacySupportVersion}"
    const val multidex = "androidx.multidex:multidex:${Version.multidexVersion}"

}

object Image {
    const val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glideVersion}"
}

object Arch {
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
    const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofitVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
    const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofitVersion}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLoggingVersion}"
}

object Json {
    const val gson = "com.google.code.gson:gson:${Version.gsonVersion}"
}

object Reactivex {
    const val android = "io.reactivex.rxjava2:rxandroid:${Version.rxandroidVersion}"
    const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Version.rxkotlinVersion}"
    const val rxBinding = "com.jakewharton.rxbinding2:rxbinding:${Version.rxbindingVersion}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Version.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
}

object TestLibs {
    const val junit = "junit:junit:${Version.junitVersion}"
    const val testRunner = "androidx.test:runner:${Version.testRunnerVersion}"
}

object UnitTest {
    const val mockitoCore = "org.mockito:mockito-core:${Version.mockitoVersion}"
    const val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoInlineVersion}"
    const val coreTesting = "android.arch.core:core-testing:${Version.coreTestingVersion}"
    const val mockitoKotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlinVersion}"
    const val daggerMock =
        "com.github.fabioCollini.daggermock:daggermock-kotlin:${Version.daggerMockVersion}"
    const val kotlinTest = "io.kotlintest:kotlintest-runner-junit5:${Version.kotlinTestVersion}"
}

object CircularImageView {
    const val circularImageView = "de.hdodenhof:circleimageview:${Version.circularImageViewVersion}"
}