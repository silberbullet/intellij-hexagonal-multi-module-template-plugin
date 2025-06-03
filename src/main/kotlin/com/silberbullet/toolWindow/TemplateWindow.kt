package com.silberbullet.toolWindow

import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import javax.swing.JButton
import javax.swing.JComponent

class TemplateWindow(toolWindow: ToolWindow) {

    fun getContent() : JComponent {
        return JBPanel<JBPanel<*>>().apply {
            add(JBLabel("Hello Tool Window!"))
            add(JButton("Click Me").apply {
                addActionListener {println("Click Me")}
            })
        }
    }
}