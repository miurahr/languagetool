plugins {
    `java-library`
    `maven-publish`
    `java-test-fixtures`
}

repositories {
    mavenCentral()
}

group = "org.omegat.lucene"
version = "6.3-omt2.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
        vendor = JvmVendorSpec.ADOPTIUM
    }
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
    options.compilerArgs = listOf("-Xlint:none")
    // options.compilerArgs = listOf("-XDenableSunApiLintControl", "-Xlint:all",  "-Werror", "-Xlint:-sunapi")
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
    setFailOnError(false)
    (options as StandardJavadocDocletOptions).addBooleanOption("Xdoclint:none", true)
    (options as StandardJavadocDocletOptions).addStringOption("Xmaxwarns", "1")
}

tasks.withType<Test> {
    minHeapSize = "512m"
    maxHeapSize = "1024m"
    jvmArgs = listOf("-XX:MaxMetaspaceSize=512m")
}
