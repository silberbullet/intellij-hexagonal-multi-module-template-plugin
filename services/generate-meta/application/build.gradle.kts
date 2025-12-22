val generateMetaApi : String by project

dependencies {
    api(project(generateMetaApi))
}