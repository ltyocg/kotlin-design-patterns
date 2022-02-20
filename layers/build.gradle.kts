dependencies {
    implementation(project(":commons"))
    implementation("org.springframework.data", "spring-data-jpa", "2.6.1")
    implementation("org.hibernate", "hibernate-core", "5.5.7.Final")
    implementation("com.h2database", "h2", "2.1.210")
    implementation(kotlin("reflect"))
}