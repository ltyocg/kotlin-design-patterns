plugins {
    kotlin("plugin.jpa") version "+"
}
dependencies {
    implementation("com.h2database", "h2", "+")
    implementation("org.hibernate.orm", "hibernate-core", "+")
}