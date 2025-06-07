package com.silberbullet.views

import com.intellij.credentialStore.RememberCheckBoxState.isSelected
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.silberbullet.services.ServiceTemplateMaker
import com.silberbullet.views.models.TemplateServiceModel
import com.silberbullet.views.models.type.ServiceMethodType
import javax.swing.JComponent

class TemplateDialog(private val project: Project) : DialogWrapper(true) {

    private val serviceModel = TemplateServiceModel()

    init {
        title = "${serviceModel.common.title} (${serviceModel.common.version})"
        serviceModel.parentDirectory = project.name

        init()
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun createCenterPanel(): JComponent {
        return panel {
            row("상위 폴더") {
                textField()
                    .bindText(serviceModel::parentDirectory)
                    .align(Align.FILL)
                    .applyToComponent {
                        text = serviceModel.parentDirectory
                    }
            }

            row("도메인 명") {
                textField()
                    .bindText(serviceModel::domainPrefix)
                    .align(Align.FILL)
                    .validationOnApply {
                        if (it.text.isBlank())
                            error("도메인 명을 입력하세요.")
                        else null
                    }
            }

            row("기본 패키지") {
                textField()
                    .bindText(serviceModel::firstPackageName)
                    .align(Align.FILL)
                    .validationOnApply {
                        if (it.text.isBlank())
                            error("기본 패키지를 입력하세요.")
                        else null
                    }
            }

            group("메소드") {
                row {
                    ServiceMethodType.entries.forEach { type ->
                        checkBox(type.name).component.addItemListener {
                            if (isSelected) {
                                if (type.code !in serviceModel.methodList) {
                                    serviceModel.methodList.add(type.code)
                                } else {
                                    serviceModel.methodList.remove(type.code)
                                }
                            }
                        }
                    }
                }
            }

            group("폴더 구조") {
                // 최상위 Map 순회
                serviceModel.domainStructure.forEach { (key, value) ->
                    when (value) {
                        is Boolean -> row() {
                            checkBox(key).bindSelected(
                                { serviceModel.domainStructure[key] as Boolean },
                                { serviceModel.domainStructure[key] = it }
                            )
                        }

                        is MutableMap<*, *> -> row(key) {
                            @Suppress("UNCHECKED_CAST")
                            val child = value as MutableMap<String, Any>
                            // 하위 Map 순회
                            child.forEach { (subKey, subValue) ->
                                if (subValue is Boolean) {
                                    checkBox(subKey).bindSelected(
                                        { child[subKey] as Boolean },
                                        { child[subKey] = it }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }.also {
            initValidation()                     // validationOnInput/Apply 활성화
            setOKButtonText("확인")
            setCancelButtonText("닫기")
        }
    }

    override fun doOKAction() {
        super.doOKAction()

        val serviceTemplateMaker = ServiceTemplateMaker()
        serviceTemplateMaker.createTemplate(serviceModel, project)
    }
}
