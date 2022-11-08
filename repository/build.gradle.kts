plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "+"
    kotlin("plugin.jpa") version "+"
}
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("com.h2database", "h2", "+")
    implementation("org.apache.commons", "commons-dbcp2", "2.9.0")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}