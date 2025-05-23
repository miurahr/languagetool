plugins {
    java
    `maven-publish`
    signing
    distribution
    `test-report-aggregation`
    alias(libs.plugins.nexus.publish)
}


tasks.wrapper {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "8.13"
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
            testSuiteName = "test"
            setGroup("verification")
        }
        tasks.getByPath("check").dependsOn(aggregateTestReport)
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
