plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.lookmyup.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.lookmyup.app"
        minSdk = 26
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    hilt {
        enableAggregatingTask = false
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    sourceSets {
        getByName("test") {
            java.srcDir("src/test/java")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/java")
        }
    }
}

dependencies {
    // Jetpack Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.tooling)
    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)

    // Image Loading (Coil for Compose)
    implementation(libs.coil.compose)

    // Room Database
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // ExoPlayer
    implementation(libs.exo.core)
    implementation(libs.exo.ui)
    implementation(libs.exo.session)

    // Hilt for Dependency Injection
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    // Lifecycle + ViewModel
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)


    // Testing
    testImplementation(libs.junit.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.truth)

    implementation(libs.accompanist.placeholder.material)

}