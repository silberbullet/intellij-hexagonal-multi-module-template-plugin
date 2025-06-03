package com.silberbullet.toolWindow

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class TemplateWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("test version")
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val templateWindow = TemplateWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(templateWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }
    override fun shouldBeAvailable(project: Project) = true
}