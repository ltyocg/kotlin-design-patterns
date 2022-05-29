plugins {
    kotlin("plugin.jpa") version "1.6.21"
}
dependencies {
    implementation("com.h2database", "h2", "2.1.212")
    implementation("org.hibernate.orm", "hibernate-core", "6.0.1.Final")
}