@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.swjjang7.searchcontentbykakao"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.swjjang7.searchcontentbykakao"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
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
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.appcompat.resources)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.gson)
    implementation(libs.glide)
    implementation(libs.coroutines)
    implementation(libs.coroutines.core)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.logging.interceptor)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.recyclerview)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

kapt {
    correctErrorTypes = true
}