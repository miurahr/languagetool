plugins {
    `maven-publish`
    signing
    `test-report-aggregation`
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