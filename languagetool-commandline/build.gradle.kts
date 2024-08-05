plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(project(":languagetool-core"))
    implementation(libs.jetbrains.annotations)
    implementation(libs.commons.io)
    runtimeOnly(project(":language-all"))
    testImplementation(libs.junit4)
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "LanguageTool command-line version"
