plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(project(":languagetool-core"))
    implementation(project(":language-de"))
    testImplementation(libs.junit4)
    testImplementation(libs.morfologik.fsa)
    testImplementation(libs.morfologik.builders)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "Simple German module for LanguageTool"
