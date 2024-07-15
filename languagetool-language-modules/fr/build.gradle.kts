
plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.french.pos.dict)
    implementation(project(":languagetool-core"))
    implementation(libs.jetbrains.annotations)
    implementation(libs.jackson.databind)
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
}

description = "French module for LanguageTool"
