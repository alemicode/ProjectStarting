import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven { url = uri("https://plugins.gradle.org/m2/") }
}

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

afterEvaluate {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            apiVersion = "1.5"
            languageVersion = "1.5"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.6.1")
    implementation("gradle.plugin.dev.arunkumar:scabbard-gradle-plugin:0.5.0")
    implementation("io.sentry:sentry-android-gradle-plugin:3.1.4")
}
