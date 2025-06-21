plugins {
    java
    `maven-publish`
    signing
    distribution
    `test-report-aggregation`
    alias(libs.plugins.nexus.publish)
}

val projectGroup: String by project
val projectVersion: String by project

group = projectGroup
version = projectVersion

tasks.wrapper {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "8.14.2"
}

val ossrhUsername: String? by project
val ossrhPassword: String? by project
nexusPublishing.repositories {
    sonatype {
        nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
        snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
        if (ossrhUsername != null && ossrhPassword != null) {
            username.set(ossrhUsername)
            password.set(ossrhPassword)
        } else {
            username.set(System.getenv("SONATYPE_USER"))
            password.set(System.getenv("SONATYPE_PASS"))
        }
    }
}

val wholeProjects by configurations.creating

repositories {
    mavenCentral()
}

dependencies {
    subprojects.forEach { proj ->
        testReportAggregation(proj)
        wholeProjects(proj)
    }
}

reporting {
    reports {
        val aggregateTestReport by creating(AggregateTestReport::class) {
            subprojects {
                val testTasks = tasks.withType<Test>()
                testTasks.configureEach {
                    ignoreFailures = true
                }
            }
        }
    }
}

distributions {
    main {
        contents {
            from(wholeProjects)
            into("libs")
        }
    }
}
