plugins {
    kotlin("plugin.serialization") version "+"
}
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    repositories {
        mavenCentral()
    }
    dependencies {
        implementation("io.ktor", "ktor-server-content-negotiation-jvm", "+")
        implementation("io.ktor", "ktor-serialization-kotlinx-json-jvm", "+")
        implementation("io.ktor", "ktor-server-core-jvm", "+")
        implementation("io.ktor", "ktor-server-netty-jvm", "+")
    }
}