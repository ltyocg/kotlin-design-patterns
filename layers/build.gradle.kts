plugins {
    kotlin("plugin.jpa") version "+"
}
dependencies {
    implementation(project(":commons"))
    implementation("org.springframework.data", "spring-data-jpa", "+")
    implementation("org.hibernate.orm", "hibernate-core", "+")
    implementation("com.h2database", "h2", "+")
}