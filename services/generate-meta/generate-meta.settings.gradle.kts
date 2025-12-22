val generateMeta: String by settings
val generateMetaApi: String by settings
val generateMetaDomain: String by settings
val generateMetaException: String by settings
val generateMetaReadModel: String by settings
val generateMetaApplication: String by settings
val generateMetaRdbAdapter: String by settings
val generateMetaRedisAdapter: String by settings
val generateMetaWebMvcAdapter: String by settings

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

val generateMetaDirectory = getDirectories("services", "generate-meta")

// SERVICE/generateMeta
include(
    generateMeta,
    generateMetaApi,
    generateMetaDomain,
//    generateMetaException,
//    generateMetaReadModel,
    generateMetaApplication,
//    generateMetaRdbAdapter,
//    generateMetaRedisAdapter,
//    generateMetaWebMvcAdapter,
)

project(generateMeta).projectDir = generateMetaDirectory("generate-meta")
project(generateMetaApi).projectDir = generateMetaDirectory("api")
project(generateMetaDomain).projectDir = generateMetaDirectory("domain")
//project(generateMetaException).projectDir = generateMetaDirectory("exception")
//project(generateMetaReadModel).projectDir = generateMetaDirectory("readmodel")
project(generateMetaApplication).projectDir = generateMetaDirectory("application")
//project(generateMetaRdbAdapter).projectDir = generateMetaDirectory("rdb")
//project(generateMetaRedisAdapter).projectDir = generateMetaDirectory("redis")
//project(generateMetaWebMvcAdapter).projectDir = generateMetaDirectory("web-mvc")