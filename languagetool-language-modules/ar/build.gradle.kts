
plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(project(":languagetool-core"))
    implementation(libs.morfologik.fsa)
    implementation(libs.morfologik.builders)
    implementation(libs.morfologik.speller)
    implementation(libs.morfologik.stemming)
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "Arabic module for LanguageTool"
