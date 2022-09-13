package plugins

import extensions.implementation
import io.sentry.android.gradle.SentryPlugin
import io.sentry.android.gradle.extensions.SentryPluginExtension

apply<SentryPlugin>()

configure<SentryPluginExtension> {

    // ignoredBuildTypes.set(listOf("debug"))
    // Disables or enables the handling of Proguard mapping for Sentry.
    // If enabled the plugin will generate a UUID and will take care of
    // uploading the mapping to Sentry. If disabled, all the logic
    // related to proguard mapping will be excluded.
    // Default is enabled.
    includeProguardMapping.set(true)

    // Whether the plugin should attempt to auto-upload the mapping file to Sentry or not.
    // If disabled the plugin will run a dry-run and just generate a UUID.
    // The mapping file has to be uploaded manually via sentry-cli in this case.
    // Default is enabled.
    autoUploadProguardMapping.set(true)

    // Experimental flag to turn on support for GuardSquare's
    // tools integration (Dexguard and External Proguard).
    // If enabled, the plugin will try to consume and upload the mapping
    // file produced by Dexguard and External Proguard.
    // Default is disabled.
    experimentalGuardsquareSupport.set(false)

    // Disables or enables the automatic configuration of Native Symbols
    // for Sentry. This executes sentry-cli automatically so
    // you don't need to do it manually.
    // Default is disabled.
    uploadNativeSymbols.set(false)

    // Does or doesn't include the source code of native code for Sentry.
    // This executes sentry-cli with the --include-sources param. automatically so
    // you don't need to do it manually.
    // Default is disabled.
    includeNativeSources.set(false)

    // Enable or disable the tracing instrumentation.
    // Does auto instrumentation for specified features through bytecode manipulation.
    // Default is enabled.
    tracingInstrumentation {
        enabled.set(true)

        // Specifies a set of instrumentation features that are eligible for bytecode manipulation.
        // Defaults to all available values of InstrumentationFeature enum class.
        features.set(
            listOf(
                io.sentry.android.gradle.extensions.InstrumentationFeature.DATABASE,
                io.sentry.android.gradle.extensions.InstrumentationFeature.FILE_IO,
                io.sentry.android.gradle.extensions.InstrumentationFeature.OKHTTP
            )
        )
    }

    // Enable auto-installation of Sentry components (sentry-android SDK and okhttp, timber and fragment integrations).
    // Default is enabled.
    // Only available v3.1.0 and above.
    autoInstallation {
        enabled.set(true)

        // Specifies a version of the sentry-android SDK and fragment, timber and okhttp integrations.
        //
        // This is also useful, when you have the sentry-android SDK already
        // included into a transitive dependency/module and want to
        // align integration versions with it
        // (if it's a direct dependency, the version will be inferred).
        //
        // NOTE: if you have a higher version of the sentry-android SDK or integrations on the
        // classpath, this setting will have no effect
        // as Gradle will resolve it to the latest version.
        //
        // Defaults to the latest published sentry version.
        sentryVersion.set("6.3.1")
    }
}

dependencies {

    implementation("io.sentry:sentry-android:6.3.1")
}
