plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(project(":languagetool-core"))
    implementation(libs.spanish.pos.dict)
    implementation("org.softcatala:spanish-pos-dict:2.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
}

description = "Spanish module for LanguageTool"
