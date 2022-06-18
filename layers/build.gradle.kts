plugins {
    kotlin("plugin.jpa") version "1.7.0"
}
dependencies {
    implementation(project(":commons"))
    implementation("org.springframework.data", "spring-data-jpa", "2.6.4")
    implementation("org.hibernate.orm", "hibernate-core", "6.0.1.Final")
    implementation("com.h2database", "h2", "2.1.212")
}