plugins {
    kotlin("plugin.jpa") version "1.6.10"
}
dependencies {
    implementation("com.h2database", "h2", "2.1.210")
    implementation("org.hibernate", "hibernate-core", "5.6.7.Final")
}