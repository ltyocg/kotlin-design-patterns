plugins {
    kotlin("plugin.jpa") version "1.9.21"
}
dependencies {
    implementation(project(":commons"))
    implementation("org.springframework.data", "spring-data-jpa", "3.2.0")
    implementation("org.hibernate.orm", "hibernate-core", "6.3.1.Final")
    implementation("com.h2database", "h2", "2.2.224")
}
