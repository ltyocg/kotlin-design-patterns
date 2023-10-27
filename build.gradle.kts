plugins {
    kotlin("jvm") version "1.9.10"
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
        implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-slf4j", "1.7.3")
        implementation("ch.qos.logback", "logback-classic", "1.4.11")
        implementation("io.github.oshai", "kotlin-logging-jvm", "5.1.0")
        implementation(kotlin("reflect"))
        testImplementation(kotlin("test"))
        testImplementation("org.junit.jupiter", "junit-jupiter-params", "5.10.0")
        testImplementation("org.mockito.kotlin", "mockito-kotlin", "5.1.0")
        testImplementation("org.mockito", "mockito-inline", "5.2.0")
    }
    tasks.test {
        useJUnitPlatform()
    }
    kotlin {
        jvmToolchain(17)
    }
}