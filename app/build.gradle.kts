@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    autowire(libs.plugins.com.android.application)
    autowire(libs.plugins.org.jetbrains.kotlin.android)
    autowire(libs.plugins.com.google.dagger.hilt.android)
    autowire(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "jxutcm.lan.localshare"
    compileSdk = 34

    defaultConfig {
        applicationId = "jxutcm.lan.localshare"
        minSdk = 24
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
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
val ktorVersion="2.3.5"
val navVersion = "2.7.4"
dependencies {
    implementation(androidx.compose.material.material.icons.extended)
    implementation(androidx.hilt.hilt.navigation.compose)
    implementation(com.google.dagger.hilt.android)
    ksp(com.google.dagger.hilt.android.compiler)
    implementation(androidx.navigation.navigation.compose)
    implementation(com.blankj.utilcodex)
    implementation(io.coil.kt.coil.compose)
    implementation(io.ktor.ktor.network)
    implementation(io.ktor.ktor.client.core)
    implementation(io.ktor.ktor.client.android)
    implementation(androidx.core.core.ktx)
    implementation(androidx.lifecycle.lifecycle.runtime.ktx)
    implementation(androidx.activity.activity.compose)
    implementation(platform(androidx.compose.compose.bom))
    implementation(androidx.compose.ui.ui)
    implementation(androidx.compose.ui.ui.graphics)
    implementation(androidx.compose.ui.ui.tooling.preview)
    implementation(androidx.compose.material3.material3)
    testImplementation(junit.junit)
    androidTestImplementation(androidx.test.ext.junit)
    androidTestImplementation(androidx.test.espresso.espresso.core)
    androidTestImplementation(platform(androidx.compose.compose.bom))
    androidTestImplementation(androidx.compose.ui.ui.test.junit4)
    debugImplementation(androidx.compose.ui.ui.tooling)
    debugImplementation(androidx.compose.ui.ui.test.manifest)
}