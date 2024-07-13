plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(project(":languagetool-core"))
    testImplementation("junit:junit:4.13.2")
    testImplementation(project(":languagetool-core"))
}

description = "Persian module for LanguageTool"
