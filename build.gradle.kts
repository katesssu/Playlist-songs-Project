plugins {
    kotlin("jvm") version "2.0.10"
    // Plugin for Dokka - KDoc generating tool
    id("org.jetbrains.dokka") version "1.9.20"
    application
    jacoco
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // dependencies for logging
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
    implementation("org.slf4j:slf4j-simple:2.0.16")

    // For generating a Dokka Site from KDoc
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
}

tasks.test {
    useJUnitPlatform()

}

// Configure JaCoCo version (optional, defaults to latest)
jacoco {
    toolVersion = "0.8.11" // Check latest version on https://www.jacoco.org/jacoco/
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // Generate report after tests
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // Ensure tests run before report
    reports {
        xml.required.set(true) // Required for CI/CD pipelines
        html.required.set(true) // Human-readable HTML report
        csv.required.set(false) // Disable CSV if not needed
    }

    // Define what files to include in coverage (Kotlin classes)
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it).apply {
                exclude(
                    "**/Q*", // Exclude generated Q-classes if using QueryDSL
                    "**/*Test*", // Exclude test classes
                    "**/*Application*" // Exclude main application class if desired
                )
            }
        })
    )
}

// Optional: Add coverage verification
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.80".toBigDecimal() // Require 80% coverage
            }
        }
    }
}

// Make sure verification runs with the report
tasks.jacocoTestReport {
    finalizedBy(tasks.jacocoTestCoverageVerification)
}


