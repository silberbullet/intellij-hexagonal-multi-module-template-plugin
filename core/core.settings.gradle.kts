val core = rootDir.resolve("core")
    .walkTopDown()
    .maxDepth(3)
    .filter(File::isDirectory)
    .associateBy(File::getName)


include(
    ":ai-core"
)

project(":ai-core").projectDir = core["ai-core"]!!
