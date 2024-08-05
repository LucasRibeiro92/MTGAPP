plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {
    namespace = "com.scout.mtgapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.scout.mtgapp"
        minSdk = 24
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /*
    * Firebase
     */
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")
    //Auth
    implementation("com.firebaseui:firebase-ui-auth:7.2.0")

    //Lifecycle
    /*

    * implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    * implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    *
    * */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    //Koin
    /*
    * implementation("io.insert-koin:koin-core:3.2.2")
    * implementation("io.insert-koin:koin-android:3.2.2")
    *
    * */
    implementation("io.insert-koin:koin-android:3.2.0")
    implementation("io.insert-koin:koin-android-compat:3.2.0")
    implementation("io.insert-koin:koin-androidx-compose:3.4.5")

    //Room
    /*
    *
    * implementation("androidx.room:room-runtime:2.3.0")
    * kapt("androidx.room:room-compiler:2.3.0")
    *
    * implementation("androidx.room:room-runtime:2.5.2")
    * kapt("androidx.room:room-compiler:2.5.2")
    * implementation("androidx.room:room-ktx:2.5.2")
    * implementation("androidx.room:room-testing:2.5.2")
    *
     * */
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-testing:2.5.1")

    /*
    *
    *  TESTS
    *
     */
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("androidx.arch.core:core-testing:2.1.0") // Para InstantTaskExecutorRule
    testImplementation("io.insert-koin:koin-test:3.2.0")
    testImplementation("io.insert-koin:koin-test-junit4:3.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}