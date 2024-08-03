plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(libs.guava)
    implementation(libs.commons.text)
    implementation(libs.opennlp.chunk.models)
    implementation(libs.opennlp.postag.models)
    implementation(libs.opennlp.tokenize.models)
    implementation(libs.opennlp.tools)
    implementation(libs.english.pos.dict)
    implementation(libs.trove4j)
    implementation(libs.indriya)
    implementation(libs.hankcs)
    implementation(project(":languagetool-core"))
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "English module for LanguageTool"

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "org.languagetool.language.en")
    }
}
