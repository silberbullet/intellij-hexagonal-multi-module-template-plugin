package com.silberbullet.views

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.silberbullet.forms.TemplateDialogForm
import com.silberbullet.views.models.TemplateServiceModel
import javax.swing.JComponent

class TemplateDialogView(private val project: Project) : DialogWrapper(true) {

    private val serviceModel = TemplateServiceModel()
    private val form = TemplateDialogForm(project)

    init {
        title = "${serviceModel.common.title} (${serviceModel.common.version})"
        serviceModel.parentDirectory = project.name

        init()
    }

    override fun createCenterPanel(): JComponent? {
        return form.rootPanel;
    }
}
