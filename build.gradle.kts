plugins {
    `maven-publish`
    signing
    alias(libs.plugins.nexus.publish)
}

val ossrhUsername: String? by project
val ossrhPassword: String? by project

nexusPublishing.repositories {
    sonatype {
        stagingProfileId = "15818299f2c2bb"
        nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
        if (ossrhUsername != null && ossrhPassword != null) {
            username.set(ossrhUsername)
            password.set(ossrhPassword)
        } else {
            username.set(System.getenv("SONATYPE_USER"))
            password.set(System.getenv("SONATYPE_PASS"))
        }
    }
}


tasks.create("testsAll", TestReport::class.java) {
    destinationDirectory = layout.buildDirectory.dir("reports/tests/all")
    project.evaluationDependsOnChildren()
    allprojects.forEach { subproject ->
        subproject.tasks.withType<Test> {
            ignoreFailures = true
            // reports.junitXml.isEnabled = true
            this@create.reportOn(this@withType)
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
