plugins {
    `maven-publish`
    signing
    `test-report-aggregation`
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

reporting {
    reports {
        tasks.create<TestReport>("aggregateTestReport") {
            destinationDirectory.set(layout.buildDirectory.dir("reports/tests/all"))
            setGroup("verification")

            // Add the test tasks from subprojects
            subprojects {
                val testTasks = tasks.withType<Test>()
                testTasks.configureEach {
                    ignoreFailures = true
                }
                testTasks.forEach { testTask ->
                    testResults.from(testTask)
                }
            }
        }
    }
}