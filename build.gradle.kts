plugins {
    `maven-publish`
    signing
    `test-report-aggregation`
    `jvm-test-suite`
    alias(libs.plugins.nexus.publish)
}


tasks.wrapper {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "8.9"
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

repositories {
    mavenCentral()
}

dependencies {
    subprojects.forEach { proj ->
        testReportAggregation(proj)
    }
}

reporting {
    reports {
        val aggregateTestReport by creating(AggregateTestReport::class) {
            testType = TestSuiteType.UNIT_TEST
            setGroup("verification")

            // Add the test tasks from subprojects
            subprojects {
                val testTasks = tasks.withType<Test>()
                testTasks.configureEach {
                    ignoreFailures = true
                }
            }
        }
    }
}