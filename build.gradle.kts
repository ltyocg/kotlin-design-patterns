import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
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
        implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-slf4j", "1.5.2")
        implementation("ch.qos.logback", "logback-classic", "1.2.5")
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