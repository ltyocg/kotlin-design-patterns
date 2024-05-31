import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("plugin.jpa") version "2.0.0"
}
dependencies {
    implementation(project(":commons"))
    implementation("com.h2database", "h2", "2.2.224")
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("org.springframework.boot", "spring-boot-starter-actuator")
    implementation("org.springframework.retry", "spring-retry")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("io.rest-assured", "rest-assured", "5.4.0")
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}
