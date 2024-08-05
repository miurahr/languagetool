plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(project(":languagetool-core"))
    implementation(project(":languagetool-commandline"))
    implementation(project(":languagetool-gui-commons"))
    implementation(project(":languagetool-server"))
    implementation(project(":languagetool-tools"))
    implementation(libs.jetbrains.annotations)
    implementation(libs.commons.collections4)
    implementation(libs.lucene.core)
    implementation(project(":language-en"))
    runtimeOnly(project(":language-all"))
    testImplementation(libs.junit4)
    testImplementation(libs.mockito.core)
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "LanguageTool stand-alone GUI"
