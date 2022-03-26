plugins {
    kotlin("plugin.jpa") version "1.6.10"
}
dependencies {
    implementation(project(":commons"))
    implementation("org.springframework.data", "spring-data-jpa", "2.6.1")
    implementation("org.hibernate", "hibernate-core", "5.6.7.Final")
    implementation("com.h2database", "h2", "2.1.210")
    implementation(kotlin("reflect"))
}