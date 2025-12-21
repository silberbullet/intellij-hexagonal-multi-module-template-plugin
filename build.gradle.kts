plugins {
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
        apply(plugin = "java-library")
        apply(plugin = "org.jetbrains.kotlin.jvm")

        extensions.configure<JavaPluginExtension>("java") {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "17"
        }
    }
}

