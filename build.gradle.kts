import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
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
        implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.5.1")
        implementation("ch.qos.logback", "logback-classic", "1.2.3")
        testImplementation(kotlin("test"))
        testImplementation("org.mockito.kotlin", "mockito-kotlin", "3.2.0")
        testImplementation("org.mockito", "mockito-inline", "3.9.0")
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}