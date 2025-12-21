val runners = rootDir.resolve("runners")
    .walkTopDown()
    .maxDepth(3)
    .filter(File::isDirectory)
    .associateBy(File::getName)


include(
    ":api-server",
    ":plugin-intellij"
)

project(":api-server").projectDir = runners["api-server"]!!
project(":plugin-intellij").projectDir = runners["plugin-intellij"]!!

apply(from = "plugin-intellij/plugin-intellij.settings.gradle.kts")