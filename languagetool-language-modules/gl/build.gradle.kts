plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(project(":languagetool-core"))
    implementation(libs.morfologik.stemming)
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
}

description = "Galician module for LanguageTool"
