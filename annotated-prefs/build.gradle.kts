plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.squareup:kotlinpoet:1.6.0")
    implementation("org.jetbrains.kotlin:kotlin-symbol-processing-api:1.4-M1-dev-experimental-20200610")
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}