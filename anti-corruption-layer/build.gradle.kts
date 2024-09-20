plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.spring") version "2.0.20"
}
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}
