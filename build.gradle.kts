import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("org.springframework.boot") version "3.4.3" apply false
    id("io.spring.dependency-management")  version "1.1.7" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.24" apply false
    id("org.jetbrains.intellij") version "1.17.3" apply false
}

allprojects {
    group = "com.silberbullet"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

subprojects {
    // IntelliJ Plugin 모듈 제외
    if (name != "plugin-intellij") {
        apply {
            plugin("java")
            plugin("java-library")
            plugin("org.springframework.boot")
            plugin("io.spring.dependency-management")
            plugin("org.jetbrains.kotlin.jvm")
            plugin("org.jetbrains.kotlin.plugin.spring")
        }

        java {
            toolchain {
                languageVersion = JavaLanguageVersion.of(21)
            }
        }

        configurations {
            compileOnly {
                extendsFrom(configurations.annotationProcessor.get())
            }
            configureEach {
                exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
            }
        }

        dependencies {
            // FIXME determine the placement of logging library later
            implementation("org.springframework.boot:spring-boot-starter-log4j2")
            implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")

            compileOnly("org.projectlombok:lombok")
            annotationProcessor("org.projectlombok:lombok")
            testCompileOnly("org.projectlombok:lombok")
            testAnnotationProcessor("org.projectlombok:lombok")

            compileOnly("org.springframework:spring-web")
            compileOnly("org.springframework:spring-context")

            implementation("org.springframework.data:spring-data-commons")

            testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
            testImplementation("io.mockk:mockk:1.13.12")
            testImplementation(kotlin("script-runtime"))
            testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
        }

        tasks.withType<BootJar> {
            enabled = false
        }

        tasks.withType<Jar>{
            enabled = true
        }

        tasks.test {
            useJUnitPlatform()
        }
    }
}

