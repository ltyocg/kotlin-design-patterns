plugins {
    kotlin("plugin.serialization") version "1.7.10"
}
dependencies {
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.4.0")
    implementation("com.amazonaws", "aws-java-sdk-dynamodb", "1.12.214")
    implementation("com.amazonaws", "aws-lambda-java-core", "1.2.1")
    implementation("com.amazonaws", "aws-lambda-java-events", "3.11.0")
}