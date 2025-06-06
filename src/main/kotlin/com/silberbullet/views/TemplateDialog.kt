package com.silberbullet.views

import com.intellij.credentialStore.RememberCheckBoxState.isSelected
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.silberbullet.views.models.TemplateServiceModel
import com.silberbullet.views.models.type.ServiceMethodType
import javax.swing.Action
import javax.swing.JComponent

class TemplateDialog : DialogWrapper(true) {

    val serviceModel = TemplateServiceModel()

    init {
        init()

        title = serviceModel.common.title + " (" + serviceModel.common.version + ")"
        okAction.putValue(Action.NAME, "생성")
        cancelAction.putValue(Action.NAME, "닫기")
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun createCenterPanel(): JComponent {
        return panel {
            row("상위 폴더") {
                textField()
                    .bindText(serviceModel::parentDirectory)
                    .align(Align.FILL)
            }
            row("도메인 명") {
                textField()
                    .bindText(serviceModel::domainPrefix)
                    .align(Align.FILL)
            }
            row("기본 패키지") {
                textField()
                    .bindText(serviceModel::firstPackageName)
                    .align(Align.FILL)
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
        }
    }

    override fun doOKAction() {
        super.doOKAction()
        println(serviceModel.methodList)
    }
}