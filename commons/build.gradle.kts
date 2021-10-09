import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

dependencies {
    implementation(kotlin("test"))
}
tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.RequiresOptIn")
}