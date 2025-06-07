package com.silberbullet.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.silberbullet.views.TemplateDialog

class OpenTemplateDialogAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = TemplateDialog(e.project!!)

        dialog.showAndGet()
    }
}