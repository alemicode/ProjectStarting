package commons

import dependencies.Dependencies

plugins {
    id("java-library")
    id("kotlin")
    id("com.android.lint")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

sourceSets {
    getByName("main") {
        java.srcDir("src/main/kotlin")
    }
    getByName("test") {
        java.srcDir("src/test/kotlin")
    }
}

lint {
    lintConfig = rootProject.file("lint.xml")
    checkAllWarnings = true
    warningsAsErrors = true
}

dependencies {

    implementation(project(Modules.Core.UTILS))
    implementation(Dependencies.KOTLINX_COROUTINES_CORE)
    implementation(Dependencies.INJECT)
}
