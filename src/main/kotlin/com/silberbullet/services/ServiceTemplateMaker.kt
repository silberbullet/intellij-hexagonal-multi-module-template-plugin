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

        // 2) domainStructure 순회
        model.domainStructure.forEach { (key, value) ->
            when (value) {
                // 최상위 Boolean 항목 (application, driving, driven 등)
                is Boolean -> {
                    if (value) {
                        val sub = File(domainDir, key)
                        if (!sub.exists()) makeDir(sub)
                    }
                }

                // "api" 처럼 중첩 Map인 경우
                is Map<*, *> -> {
                    // 먼저 api 폴더 자체 생성
                    val apiDir = File(domainDir, key)
                    if (!apiDir.exists()) makeDir(apiDir)

                    // 내부 Map(Boolean) 순회
                    @Suppress("UNCHECKED_CAST")
                    (value as Map<String, Boolean>).forEach { (subKey, enabled) ->
                        if (enabled) {
                            val sub = File(apiDir, subKey)
                            if (!sub.exists()) makeDir(sub)
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
        // 리소스에서 템플릿 읽어오기
        val tplStream = javaClass.classLoader
            .getResourceAsStream("templates/domain-build.gradle.kts.tpl")
            ?: throw IllegalStateException("템플릿을 찾을 수 없습니다.")
        val tpl = tplStream.bufferedReader().use { it.readText() }

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
                    subMap.filterValues { it }
                        .keys
                        .forEach { subKey ->
                            // "web-mvc" → "WebMvc", "rdb" → "Rdb" 등으로 변환
                            val adapterSuffix = subKey
                                .split('-', '_')
                                .joinToString("") { it.replaceFirstChar(Char::uppercase) }
                            pros += "val ${model.domainPrefix}${adapterSuffix}Adapter: String by project"
                            deps += "    api(project(${model.domainPrefix}${adapterSuffix}Adapter))"
                        }
                }
            }
        }

        val prosBlock = pros.joinToString("\n")
        val depsBlock = deps.joinToString("\n")

        // 최종 스크립트
        val script = tpl
            .replace("{{properties}}", prosBlock)
            .replace("{{dependencies}}", depsBlock)

        // 파일 쓰기
        File(domainDir, "build.gradle.kts").writeText(script)
    }
}