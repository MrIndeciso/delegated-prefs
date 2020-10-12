plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("symbol-processing") version "1.4.10-dev-experimental-20201009"
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.github.mrindeciso.delegatedpreferences_demo"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions.exclude("META-INF/main.kotlin_module")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.3.2")
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation("com.google.android.material:material:1.3.0-alpha03")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")

    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.0")

    implementation("com.squareup.moshi:moshi:1.11.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.11.0")

    implementation(project(":delegatedprefs"))
    implementation(project(":delegatedprefs-enum"))
    implementation(project(":delegatedprefs-crypto"))
    implementation(project(":delegatedprefs-flow"))
    implementation(project(":delegatedprefs-gson"))
    implementation(project(":delegatedprefs-livedata"))
    implementation(project(":delegatedprefs-moshi"))
    implementation(project(":delegatedprefs-ui"))
    implementation(project(":annotated-prefs-android"))

    implementation(project(":annotated-prefs"))
    ksp(project(":annotated-prefs"))

}
