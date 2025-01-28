plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.project.kotlinTestingProject"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
        useJUnitPlatform()

        reports {
            html.required.set(true)
            junitXml.required.set(true)
        }
}

kotlin {
    jvmToolchain(20)
}