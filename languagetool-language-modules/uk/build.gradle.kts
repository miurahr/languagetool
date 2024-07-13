plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(project(":languagetool-core"))
    implementation(libs.ukrainian.lt)
    implementation(libs.commons.lang)
    testImplementation(project(":languagetool-core"))
    testImplementation(libs.junit4)
    testImplementation(testFixtures(project(":languagetool-core")))
    testImplementation(libs.logback.classic)
}

description = "Ukrainian module for LanguageTool"

tasks.withType<Test> {
    minHeapSize = "512m"
    maxHeapSize = "2048m"
    jvmArgs = listOf("-XX:MaxMetaspaceSize=512m")
}
