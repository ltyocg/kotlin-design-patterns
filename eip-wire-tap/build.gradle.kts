import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.6.10"
}
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter")
    implementation("org.apache.camel", "camel-spring-boot", "3.0.1")
    implementation("org.apache.camel", "camel-core", "3.0.1")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.apache.camel", "camel-test-spring", "3.0.1")
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}