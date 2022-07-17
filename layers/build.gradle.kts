plugins {
    kotlin("plugin.jpa") version "1.7.10"
}
dependencies {
    implementation(project(":commons"))
    implementation("org.springframework.data", "spring-data-jpa", "2.7.1")
    implementation("org.hibernate.orm", "hibernate-core", "6.1.0.Final")
    implementation("com.h2database", "h2", "2.1.214")
}