import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
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
        implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-slf4j", "1.6.1")
        implementation("ch.qos.logback", "logback-classic", "1.2.11")
        implementation(kotlin("reflect"))
        testImplementation(kotlin("test"))
        testImplementation("org.junit.jupiter", "junit-jupiter-params", "5.8.2")
        testImplementation("org.mockito.kotlin", "mockito-kotlin", "4.0.0")
        testImplementation("org.mockito", "mockito-inline", "4.5.1")
    }
    tasks.test {
        useJUnitPlatform()
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}