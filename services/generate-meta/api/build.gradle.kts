val generateMetaDomain: String by project

dependencies {
    api(project(generateMetaDomain))
}