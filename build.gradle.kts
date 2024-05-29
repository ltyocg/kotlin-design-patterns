plugins {
    kotlin("jvm") version "2.0.0"
}
repositories {
    mavenCentral()
}
group = "com.ltyocg"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    repositories {
        mavenCentral()
    }
    dependencies {
        implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-slf4j", "1.8.0")
        implementation("ch.qos.logback", "logback-classic", "1.5.6")
        implementation("io.github.oshai", "kotlin-logging-jvm", "6.0.9")
        implementation(kotlin("reflect"))
        testImplementation(kotlin("test"))
        testImplementation("org.junit.jupiter", "junit-jupiter-params", "5.10.2")
        testImplementation("org.mockito", "mockito-core", "5.11.0")
        testImplementation("org.mockito", "mockito-junit-jupiter", "5.11.0")
        testImplementation("org.mockito.kotlin", "mockito-kotlin", "5.3.1")
        testImplementation("org.mockito", "mockito-inline", "5.2.0")
    }
    tasks.test {
        useJUnitPlatform()
    }
    kotlin {
        jvmToolchain(21)
    }
}
