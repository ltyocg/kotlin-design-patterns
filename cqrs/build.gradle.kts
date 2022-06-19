plugins {
    kotlin("plugin.jpa") version "1.7.0"
}
dependencies {
    implementation("com.h2database", "h2", "2.1.214")
    implementation("org.hibernate.orm", "hibernate-core", "6.1.0.Final")
}