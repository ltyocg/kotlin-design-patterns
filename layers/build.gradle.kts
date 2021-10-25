dependencies {
    implementation(project(":commons"))
    implementation("org.springframework.data", "spring-data-jpa", "2.5.6")
    implementation("org.hibernate", "hibernate-core", "5.5.7.Final")
    implementation("com.h2database", "h2", "1.4.200")
    implementation(kotlin("reflect"))
}