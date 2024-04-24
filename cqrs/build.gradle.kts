plugins {
    kotlin("plugin.jpa") version "1.9.23"
}
dependencies {
    implementation("com.h2database", "h2", "2.2.224")
    implementation("org.hibernate.orm", "hibernate-core", "6.3.1.Final")
}
