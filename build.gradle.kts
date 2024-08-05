plugins {
    `maven-publish`
    signing
    alias(libs.plugins.nexus.publish)
}


tasks.wrapper {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "8.9"
}


val sonatypeUsername: String? by project
val sonatypePassword: String? by project

nexusPublishing.repositories {
    sonatype {
        // stagingProfileId = "121f28671d24dc"
        if (sonatypeUsername != null && sonatypePassword != null) {
            username.set(sonatypeUsername)
            password.set(sonatypePassword)
        } else {
            username.set(System.getenv("SONATYPE_USER"))
            password.set(System.getenv("SONATYPE_PASS"))
        }
    }
}

tasks.create<TestReport>("testsAll") {
    destinationDirectory = layout.buildDirectory.dir("reports/tests/all")
    project.evaluationDependsOnChildren()
    allprojects {
        tasks.withType<Test> {
            ignoreFailures = true
            finalizedBy(this@create)
            //this@create.reportOn(this@withType)
        }
    }
    doLast {
        val reportFile = layout.buildDirectory.file("reports/tests/all/index.html").get().asFile
        val successRegex = """(?s)<div class="infoBox" id="failures">\s*<div class="counter">0<\/div>""".toRegex()
        if (!successRegex.containsMatchIn(reportFile.readText())) {
            throw GradleException("There were failing tests. See the report at: ${reportFile.toURI()}")
        }
    }
    setGroup("verification")
}
