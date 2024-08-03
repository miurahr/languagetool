plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    api(project(":languagetool-core"))
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "Romanian module for LanguageTool"

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "org.languagetool.language.ro")
    }
}
