package com.silberbullet.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.silberbullet.views.TemplateDialogView

class OpenTemplateDialogAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = TemplateDialogView(e.project!!)

        dialog.showAndGet()
    }
}