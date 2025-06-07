package com.silberbullet.services

import com.intellij.openapi.project.Project
import com.silberbullet.views.models.common.TemplateModel

interface TemplateCreator<T : TemplateModel> {

    fun createTemplate(model: T , project: Project)
}