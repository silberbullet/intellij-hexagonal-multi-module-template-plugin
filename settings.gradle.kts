rootProject.name = "hexagonal-multi-module-template"

var services = rootDir.resolve("services")

apply(from = "runners/runners.settings.gradle.kts")
apply(from = "${services}/generate-meta/generate-meta.settings.gradle.kts")