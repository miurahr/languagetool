plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(project(":languagetool-core"))
    implementation(libs.jetbrains.annotations)
    implementation(libs.commons.lang)
    testImplementation(libs.junit4)
}

description = "LanguageTool common GUI classes"
