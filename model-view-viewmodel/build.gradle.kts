import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.spring") version "2.0.0"
}
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-web")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}
tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}
