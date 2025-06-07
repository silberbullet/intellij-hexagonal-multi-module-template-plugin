package com.silberbullet.views

import com.intellij.credentialStore.RememberCheckBoxState.isSelected
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.silberbullet.views.models.TemplateServiceModel
import com.silberbullet.views.models.type.ServiceMethodType
import javax.swing.JComponent

class TemplateDialog(project: Project) : DialogWrapper(true) {

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
        }.also {
            initValidation()                     // validationOnInput/Apply 활성화
            setOKButtonText("확인")
            setCancelButtonText("닫기")
        }
    }

    override fun doOKAction() {
        super.doOKAction()
        println(serviceModel.domainPrefix)
    }
}
