rootProject.name = "hexagonal-multi-module-template"

var services = rootDir.resolve("services")

apply(from = "runner/runner.settings.gradle.kts")
apply(from = "core/core.settings.gradle.kts")
apply(from = "${services}/generate-meta/generate-meta.settings.gradle.kts")
