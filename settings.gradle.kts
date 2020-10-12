include(":annotated-prefs-android")
pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "kotlin-ksp",
                "symbol-processing",
                "org.jetbrains.kotlin.kotlin-ksp",
                "org.jetbrains.kotlin.ksp" ->
                    useModule("com.google.devtools.ksp:symbol-processing:${requested.version}")
            }
        }
    }
    repositories {
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        google()
    }
}

rootProject.name = "DelegatedPreferences"

include(":annotated-prefs")
include(":delegatedprefs-enum")
include(":delegatedprefs-livedata")
include(":delegatedprefs-flow")
include(":delegatedprefs-ui")
include(":delegatedprefs-gson")
include(":app")
include(":delegatedprefs")
include(":delegatedprefs-crypto")
include(":delegatedprefs-moshi")