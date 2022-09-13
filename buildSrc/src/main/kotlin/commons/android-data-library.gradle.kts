package commons

import dependencies.AnnotationProcessorsDependencies
import dependencies.DebugDependencies
import dependencies.Dependencies
import dependencies.ReleaseDependencies
import extensions.implementation

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = AndroidConfigs.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfigs.MIN_SDK
        targetSdk = AndroidConfigs.TARGET_SDK

        testInstrumentationRunner = AndroidConfigs.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(Build.RELEASE) {
            isMinifyEnabled = ReleaseBuild.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                rootProject.file("buildSrc/retrofit-proguard-rules.pro"),
                rootProject.file("buildSrc/gson-proguard-rules.pro"),
                "proguard-rules.pro"
            )
        }

        getByName(Build.DEBUG) {
            isMinifyEnabled = DebugBuild.isMinifyEnabled
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.Core.UTILS))

    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_ANDROID)
    kapt(AnnotationProcessorsDependencies.DAGGER_COMPILER)
    kapt(AnnotationProcessorsDependencies.DAGGER_ANDROID_PROCESSOR)

    implementation(Dependencies.KOTLINX_COROUTINES_CORE)

    implementation(Dependencies.DATASTORE_PREFERENCES)

    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.CONVERTER_GSON)

    implementation(Dependencies.GSON)

    debugImplementation(DebugDependencies.CHUCKER)
    releaseImplementation(ReleaseDependencies.CHUCKER)
}
