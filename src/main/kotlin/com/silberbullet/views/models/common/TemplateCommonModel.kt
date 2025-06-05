package com.silberbullet.views.models.common

import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.application.ApplicationNamesInfo

data class TemplateCommonModel (
    val title: String = ApplicationNamesInfo.getInstance().fullProductName,
    val version: String = ApplicationInfo.getInstance().fullVersion,
)