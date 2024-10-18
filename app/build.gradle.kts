plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("kotlin-parcelize") // Substitua "kotlin-android-extensions" por "kotlin-parcelize"
}

android {
    namespace = "com.scout.mtgapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.scout.mtgapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.car.ui.lib)
    implementation(libs.coil.compose)
    implementation(libs.firebase.analytics)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.activity.compose)

    /*
    *
    *  FIREBASE
    *
     */
    // Import the Firebase BoM
    implementation(libs.firebase.bom)
    // When using the BoM, don't specify versions in Firebase dependencies
    //Auth
    implementation(libs.firebase.ui.auth)

    /*
    *
    *  LIFECYCLE
    *
     */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")

    /*
    *
    *  KOIN
    *
     */
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation(libs.koin.android.compat)
    implementation(libs.koin.androidx.compose)

    /*
    *
    *  ROOM
    *
     */
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-testing:2.6.1")

    /*
    *
    *  GRAPHICS
    *
     */
    implementation("com.squareup.picasso:picasso:2.71828")

    /*
    *
    *  COMPOSE
    *
     */
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    /*
    *
    *  TESTS
    *
     */
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing) // Para InstantTaskExecutorRule
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)
    testImplementation(libs.kotlinx.coroutines.test)

    // ViewModel e StateFlow
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
}