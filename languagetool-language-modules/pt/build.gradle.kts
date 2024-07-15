plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation(project(":languagetool-core"))
    implementation(libs.portuguese.pos.dict)
    implementation(libs.commons.lang)
    implementation(libs.indriya)
    implementation(libs.hankcs)
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
    testImplementation(testFixtures(project(":languagetool-core")))
}

description = "Portuguese module for LanguageTool"

tasks.withType<Test> {
  minHeapSize = "512m"
  maxHeapSize = "2048m"
  jvmArgs = listOf("-XX:MaxMetaspaceSize=512m")
}