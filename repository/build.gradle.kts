plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.spring") version "1.9.21"
    kotlin("plugin.jpa") version "1.9.21"
}
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("com.h2database", "h2", "2.2.224")
    implementation("org.apache.commons", "commons-dbcp2", "2.9.0")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}
