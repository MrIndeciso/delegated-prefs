buildscript {
    val kotlin_version by extra("1.4-M2")
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0")
        classpath(kotlin("gradle-plugin", "1.4-M2"))
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}