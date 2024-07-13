
plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(project(":languagetool-core"))
    implementation(libs.jetbrains.annotations)
    implementation(libs.morfologik.stemming)
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "Arabic module for LanguageTool"
