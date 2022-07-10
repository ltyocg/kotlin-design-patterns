plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.7.10"
    kotlin("plugin.jpa") version "1.7.10"
}
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("com.h2database", "h2", "2.1.214")
    implementation("org.apache.commons", "commons-dbcp2", "2.9.0")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}