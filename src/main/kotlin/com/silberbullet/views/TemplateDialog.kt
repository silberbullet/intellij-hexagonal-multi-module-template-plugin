package com.silberbullet.views

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.silberbullet.views.models.TemplateServiceModel
import javax.swing.JComponent

class TemplateDialog : DialogWrapper(true) {

    val serviceModel = TemplateServiceModel()

    init {
        init()
        title = serviceModel.common.title+" ("+ serviceModel.common.version + ")"
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row("상위 폴더:") {
                textField()
                    .bindText(serviceModel::parentDirectory)
                    .align(Align.FILL)
            }
            row("도메인 명:") {
                textField()
                    .bindText(serviceModel::domainPrefix)
                    .align(Align.FILL)
            }
            row("기본 패키지:") {
                textField()
                    .bindText(serviceModel::firstPackageName)
                    .align(Align.FILL)
            }
        }
    }

    override fun doOKAction() {
        super.doOKAction()
        println(serviceModel.parentDirectory)
    }
}