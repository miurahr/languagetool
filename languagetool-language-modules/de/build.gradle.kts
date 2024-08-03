plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(libs.german.pos.dict)
    implementation(libs.jwordsplitter)
    implementation(libs.openregex)
    implementation(libs.guava)
    implementation(libs.trove4j)
    implementation(libs.commons.lang)
    implementation(libs.indriya)
    implementation(libs.hankcs)
    implementation(libs.commons.text)
    implementation(libs.morfologik.speller)
    implementation(project(":languagetool-core"))
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(libs.morfologik.fsa)
    testImplementation(libs.morfologik.builders)
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "German module for LanguageTool"

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "org.languagetool.language.de")
    }
}
