plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("plugin.jpa") version "2.0.0"
}
dependencies {
    implementation(project(":commons"))
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("com.h2database", "h2", "2.2.224")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}
