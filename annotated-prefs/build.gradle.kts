plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.squareup:kotlinpoet:1.6.0")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.4.10-dev-experimental-20201009")
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}