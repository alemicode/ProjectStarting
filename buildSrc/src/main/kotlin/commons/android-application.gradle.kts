package commons

import dependencies.AnnotationProcessorsDependencies
import dependencies.DebugDependencies
import dependencies.Dependencies
import extensions.implementation

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
}

android {
    compileSdk = AndroidConfigs.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidConfigs.APPLICATION_ID
        minSdk = AndroidConfigs.MIN_SDK
        targetSdk = AndroidConfigs.TARGET_SDK
        versionCode = AndroidConfigs.VERSION_CODE
        versionName = AndroidConfigs.VERSION_NAME
        testInstrumentationRunner = AndroidConfigs.TEST_INSTRUMENTATION_RUNNER
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName(Build.RELEASE) {
            isMinifyEnabled = ReleaseBuild.isMinifyEnabled
            isShrinkResources = ReleaseBuild.isShrinkResources
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName(Build.DEBUG) {
            isMinifyEnabled = DebugBuild.isMinifyEnabled
            isShrinkResources = DebugBuild.isShrinkResources
            applicationIdSuffix = DebugBuild.applicationIdSuffix
            versionNameSuffix = DebugBuild.versionNameSuffix
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    lint {
        lintConfig = rootProject.file("lint.xml")
        checkAllWarnings = true
        warningsAsErrors = true
    }
}

// Allow references to generated code for dagger
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.Features.HOME))
    implementation(project(Modules.COMMONS))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.DATA))
    implementation(project(Modules.Core.UTILS))
    implementation(project(Modules.Core.VIEWS))

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)

    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_ANDROID)
    kapt(AnnotationProcessorsDependencies.DAGGER_COMPILER)
    kapt(AnnotationProcessorsDependencies.DAGGER_ANDROID_PROCESSOR)

    implementation(Dependencies.KOTLINX_COROUTINES_ANDROID)
    implementation(Dependencies.EVENT_BUS)

    implementation(Dependencies.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_UI_KTX)

    debugImplementation(DebugDependencies.LEAKCANARY_ANDROID)
}
