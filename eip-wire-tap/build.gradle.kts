import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
}
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.apache.camel.springboot", "camel-spring-boot-starter", "3.16.0")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.apache.camel", "camel-test-spring-junit5", "3.16.0")
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}