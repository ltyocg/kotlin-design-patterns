plugins {
    kotlin("plugin.serialization") version "1.9.23"
}
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    repositories {
        mavenCentral()
    }
    dependencies {
        implementation("io.ktor", "ktor-server-content-negotiation-jvm", "2.3.6")
        implementation("io.ktor", "ktor-serialization-kotlinx-json-jvm", "2.3.6")
        implementation("io.ktor", "ktor-server-core-jvm", "2.3.6")
        implementation("io.ktor", "ktor-server-netty-jvm", "2.3.6")
    }
}
