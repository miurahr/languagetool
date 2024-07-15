plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(project(":languagetool-core"))
    implementation(libs.dutch.pos.dict)
    implementation(libs.commons.lang)
    implementation(libs.hankcs)
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
}

description = "Dutch module for LanguageTool"

tasks.withType<Test> {
  minHeapSize = "512m"
  maxHeapSize = "2048m"
  jvmArgs = listOf("-XX:MaxMetaspaceSize=512m")
}