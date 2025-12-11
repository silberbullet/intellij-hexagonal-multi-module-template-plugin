package com.silberbullet.services

import com.intellij.openapi.project.Project
import com.silberbullet.views.models.TemplateServiceModel
import java.io.File

class ServiceTemplateMaker : TemplateCreator<TemplateServiceModel> {

    override fun createTemplate(model: TemplateServiceModel, project: Project) {
        // 상위 폴더 탐색 및 생성
        var parentAbsolutePath: String = project.basePath.toString()

        if (model.parentDirectory != project.name) {
            parentAbsolutePath = makeParentDirectory(model.parentDirectory, project)
        }

        // 도메인 폴더 생성 및 basePackage 지정
        makeDomainTemplate(parentAbsolutePath, model)
    }

    private fun makeParentDirectory(parentDirectory: String, project: Project): String {
        val dir = File(project.basePath + File.separator + parentDirectory)

        // 존재하지 않으면 전체 경로의 폴더(하위 포함) 생성
        if (!dir.exists()) makeDir(dir)

        println(">> 생성 시도 디렉터리: ${dir.absolutePath}")

        return dir.absolutePath
    }

    private fun makeDomainTemplate(parentAbsolutePath: String, model: TemplateServiceModel) {
        // 1) 최상위 domain 폴더
        val domainDir = File(parentAbsolutePath, model.domainPrefix)
        if (!domainDir.exists()) {
            if (!domainDir.mkdirs()) {
                throw RuntimeException("도메인 폴더 생성 실패: ${domainDir.absolutePath}")
            }
        }

        // domain > build.gradle.kts 생성
        writeDomainBuildScript(domainDir, model)

        // domain > domain.settings.gradle.kts 생성
        writeDomainSettingScript(domainDir, model)

        // 2) domainStructure 순회
        model.domainStructure.forEach { (key, value) ->
            when (key) {
                "application" -> {
                    if (value as Boolean) {
                        val sub = File(domainDir, key)
                        if (!sub.exists()) makeDir(sub)

                        // 패키지 생성
                        // port
                        val port = File(sub, "/src/main/java/${model.firstPackageName}/${model.domainPrefix}/port")
                        if (!port.exists()) makeDir(port)
                        // service
                        val service =
                            File(sub, "/src/main/java/${model.firstPackageName}/${model.domainPrefix}/service")
                        if (!service.exists()) makeDir(service)
                        // usecase
                        val usecase =
                            File(sub, "/src/main/java/${model.firstPackageName}/${model.domainPrefix}/usecase")
                        if (!usecase.exists()) makeDir(usecase)
                    }
                }

                else -> {
                    // 상위 폴더 생성
                    val topDir = File(domainDir, key)
                    if (!topDir.exists()) makeDir(topDir)

                    // 내부 Map(Boolean) 순회
                    @Suppress("UNCHECKED_CAST") (value as Map<String, Boolean>).forEach { (subKey, enabled) ->
                        if (enabled) {
                            val sub = File(topDir, subKey)
                            if (!sub.exists()) makeDir(sub)

                            // 하위 패키지 생성
                            val subPackage = if (subKey.contains("web")) {
                                File(
                                    sub,
                                    "/src/main/java/${model.firstPackageName}/${model.domainPrefix}/web"
                                )
                            } else {
                                File(
                                    sub,
                                    "/src/main/java/${model.firstPackageName}/${model.domainPrefix}/$subKey"
                                )
                            }

                            if (!subPackage.exists()) makeDir(subPackage)
                        }
                    }
                }
            }
        }
    }

    private fun makeDir(dir: File) {
        val created = dir.mkdirs()
        if (!created) {
            // 생성 실패 처리
            throw RuntimeException("폴더 생성 실패: ${dir.absolutePath}")
        }
    }

    private fun writeDomainBuildScript(domainDir: File, model: TemplateServiceModel) {
        val tpl = readTplFile("templates/domain-build.gradle.kts.tpl")

        // dependencies 블록 동적 생성
        val pros = mutableListOf<String>()
        val deps = mutableListOf<String>()

        model.domainStructure.forEach { (key, value) ->
            when (key) {
                "api" -> {
                    val apiMap = value as Map<String, Boolean>
                    if (apiMap.any { it.value }) {
                        pros += "val ${model.domainPrefix}Api: String by project"
                        deps += "    api(project(${model.domainPrefix}Api))"
                    }
                }

                "application" -> {
                    if (value as Boolean) {
                        pros += "val ${model.domainPrefix}Application: String by project"
                        deps += "    api(project(${model.domainPrefix}Application))"
                    }
                }

                "driving", "driven" -> {
                    val subMap = value as Map<String, Boolean>
                    subMap.filterValues { it }.keys.forEach { subKey ->
                        // "web-mvc" → "WebMvc", "rdb" → "Rdb" 등으로 변환
                        val adapterSuffix =
                            subKey.split('-', '_').joinToString("") { it.replaceFirstChar(Char::uppercase) }
                        pros += "val ${model.domainPrefix}${adapterSuffix}Adapter: String by project"
                        deps += "    api(project(${model.domainPrefix}${adapterSuffix}Adapter))"
                    }
                }
            }
        }

        val prosBlock = pros.joinToString("\n")
        val depsBlock = deps.joinToString("\n")

        // 최종 스크립트
        val script = tpl.replace("{{properties}}", prosBlock).replace("{{dependencies}}", depsBlock)

        // 파일 쓰기
        File(domainDir, "build.gradle.kts").writeText(script)
    }

    private fun writeDomainSettingScript(domainDir: File, model: TemplateServiceModel) {
        val tpl = readTplFile("templates/domain-settings.gradle.kts.tpl")

        val propertiesTplState = "val {{prefix}}: String by settings"
        val projectDirTplState = "project({{prefix}}).projectDir = ${model.domainPrefix}Directory(\"{{key}}\")"

        // dependencies 블록 동적 생성
        val include = mutableListOf<String>()
        val pros = mutableListOf<String>()
        val projectDir = mutableListOf<String>()

        include += "    ${model.domainPrefix},"
        pros += propertiesTplState.replace("{{prefix}}", model.domainPrefix)

        model.domainStructure.forEach { (key, value) ->
            when (key) {
                "api" -> {
                    val apiMap = value as Map<String, Boolean>
                    if (apiMap.filterValues { it }.isNotEmpty()) {
                        include += "    ${model.domainPrefix}Api,"
                        pros += propertiesTplState.replace("{{prefix}}", "${model.domainPrefix}Api")
                        projectDir += projectDirTplState.replace("{{prefix}}", "${model.domainPrefix}Api")
                            .replace("{{key}}", key)
                    }

                    apiMap.entries.filter { it.value }.forEach { (key) ->
                        val upperKey = key.replaceFirstChar { it.uppercase() }
                        include += "    ${model.domainPrefix}${upperKey},"
                        pros += propertiesTplState.replace("{{prefix}}", "${model.domainPrefix}${upperKey}")
                        projectDir += projectDirTplState.replace("{{prefix}}", "${model.domainPrefix}${upperKey}")
                            .replace("{{key}}", key)
                    }
                }

                "application" -> {
                    if (value as Boolean) {
                        include += "    ${model.domainPrefix}Application,"
                        pros += propertiesTplState.replace("{{prefix}}", "${model.domainPrefix}Application")
                        projectDir += projectDirTplState.replace("{{prefix}}", "${model.domainPrefix}Application")
                            .replace("{{key}}", key)
                    }
                }

                "driving", "driven" -> {
                    val subMap = value as Map<String, Boolean>
                    subMap.filterValues { it }.keys.forEach { subKey ->
                        // "web-mvc" → "WebMvc", "rdb" → "Rdb" 등으로 변환
                        val adapterSuffix =
                            subKey.split('-', '_').joinToString("") { it.replaceFirstChar(Char::uppercase) }

                        include += "    ${model.domainPrefix}${adapterSuffix}Adapter,"
                        pros += propertiesTplState.replace(
                            "{{prefix}}", "${model.domainPrefix}${adapterSuffix}Adapter"
                        )
                        projectDir += projectDirTplState.replace(
                            "{{prefix}}", "${model.domainPrefix}${adapterSuffix}Adapter"
                        ).replace("{{key}}", subKey)
                    }
                }
            }
        }

        val prosBlock = pros.joinToString("\n")
        val includeBlock = include.joinToString("\n")
        val projectDirBlock = projectDir.joinToString("\n")

        // 최종 스크립트
        val script = tpl.replace("{{prefix}}", model.domainPrefix).replace("{{properties}}", prosBlock)
            .replace("{{include}}", includeBlock).replace("{{projectDir}}", projectDirBlock)

        // 파일 쓰기
        File(domainDir, "${model.domainPrefix}.settings.gradle.kts").writeText(script)
    }

    private fun readTplFile(source: String): String {
        // 리소스에서 템플릿 읽어오기
        val tplStream =
            javaClass.classLoader.getResourceAsStream(source) ?: throw IllegalStateException("템플릿을 찾을 수 없습니다.")

        return tplStream.bufferedReader().use { it.readText() }
    }
}