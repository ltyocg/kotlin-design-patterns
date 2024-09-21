plugins {
    kotlin("plugin.jpa") version "2.0.20"
}
dependencies {
    implementation("com.h2database", "h2", "2.3.232")
    implementation("org.hibernate.orm", "hibernate-core", "6.3.1.Final")
}
