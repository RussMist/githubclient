plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.russmsit.githubclient"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_1_8}"
    }
}

dependencies {
    implementation(project(Dependencies.Project.domain))
    implementation(project(Dependencies.Project.githubCore))
    implementation(Dependencies.androidCoreLibs)
    implementation(Dependencies.androidUiLibs)
    implementation(Dependencies.androidLifecycleLibs)
    implementation(Dependencies.koinLibs)
    implementation(Dependencies.facebookShimmer)
    implementation(Dependencies.androidPaging)
    implementation(Dependencies.coil)
    implementation(Dependencies.navLibs)

    androidTestImplementation(Dependencies.androidTestLibs)
    testImplementation(Dependencies.testLibs)
}