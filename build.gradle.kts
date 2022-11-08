import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "+"
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
        implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-slf4j", "+")
        implementation("ch.qos.logback", "logback-classic", "+")
        implementation(kotlin("reflect"))
        testImplementation(kotlin("test"))
        testImplementation("org.junit.jupiter", "junit-jupiter-params", "+")
        testImplementation("org.mockito.kotlin", "mockito-kotlin", "+")
        testImplementation("org.mockito", "mockito-inline", "+")
    }
    tasks.test {
        useJUnitPlatform()
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}