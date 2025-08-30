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


// Configure JaCoCo version (optional, defaults to latest)
jacoco {
    toolVersion = "0.8.11" // Check latest version on https://www.jacoco.org/jacoco/
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // Ensure tests run before report
    reports {
        xml.required.set(true) // Required for CI/CD pipelines
        html.required.set(true) // Human-readable HTML report
        csv.required.set(false) // Disable CSV if not needed
    }

    // Define what files to include in coverage
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it).apply {
                exclude(
                    "**/*Test*",
                    "**/*Application*"
                )
            }
        })
    )
}

tasks.jacocoTestCoverageVerification {
    enabled = false
}

// Make sure verification runs with the report
tasks.jacocoTestReport {
    finalizedBy(tasks.jacocoTestCoverageVerification)
}

allprojects { apply(plugin = "jacoco") }