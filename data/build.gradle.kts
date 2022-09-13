import dependencies.AndroidTestDependencies
import dependencies.TestDependencies

plugins {
    id(Commons.ANDROID_DATA_LIBRARY)
}

dependencies {
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(AndroidTestDependencies.EXT_JUNIT)
    androidTestImplementation(AndroidTestDependencies.ESPRESSO)
}
