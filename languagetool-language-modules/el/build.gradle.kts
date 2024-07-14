plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation("org.ioperm:morphology-el:1.0.0")
    implementation(project(":languagetool-core"))
    testImplementation("junit:junit:4.13.2")
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "Greek module for LanguageTool"
