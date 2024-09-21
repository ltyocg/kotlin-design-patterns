plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.spring") version "2.0.20"
    kotlin("plugin.jpa") version "2.0.20"
}
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("com.h2database", "h2", "2.3.232")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}
