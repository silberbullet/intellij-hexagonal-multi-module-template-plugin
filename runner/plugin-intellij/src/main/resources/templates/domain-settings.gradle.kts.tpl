{{properties}}

fun getDirectories(vararg names: String): (String) -> File {
    var dir = rootDir
    for (name in names) {
        dir = dir.resolve(name)
    }
    return { targetName ->
        val directory = dir.walkTopDown().maxDepth(3)
            .filter(File::isDirectory)
            .associateBy { it.name }
        directory[targetName] ?: throw Error("그런 폴더가 없습니다: $targetName")
    }
}

val {{prefix}}Directory = getDirectories("services", "{{prefix}}")

// SERVICE/{{prefix}}
include(
{{include}}
)

project({{prefix}}).projectDir = {{prefix}}Directory("{{prefix}}")
{{projectDir}}