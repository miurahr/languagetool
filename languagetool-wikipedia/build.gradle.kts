plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(project(":languagetool-core"))
    implementation(libs.jetbrains.annotations)
    implementation(libs.commons.cli)
    implementation(libs.commons.codec)
    implementation(libs.commons.compress)
    implementation(libs.commons.io)
    implementation(libs.lucene.analyzers.common)
    implementation(libs.lucene.queries)
    implementation(libs.lucene.sandbox)
    implementation(libs.swc.engine)
    implementation(libs.swc.parser.lazy)
    implementation(libs.tukaani.xz)
    runtimeOnly(libs.logback.classic)
    runtimeOnly(project(":language-all"))
    testImplementation(libs.junit4)
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.lucene.test.framework)
    testImplementation(libs.logback.classic)
}

description = "LanguageTool wikipedia tools"
