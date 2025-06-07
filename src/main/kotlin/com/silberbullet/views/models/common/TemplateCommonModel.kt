package com.silberbullet.views.models.common

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId

data class TemplateCommonModel(
    val title: String = PluginManagerCore
        .getPlugin(PluginId.getId("io.github.silberbullet.hexagonal-multi-module-template"))
        ?.name
        .orEmpty(),
    val version: String = PluginManagerCore
        .getPlugin(PluginId.getId("io.github.silberbullet.hexagonal-multi-module-template"))
        ?.version
        .orEmpty()
)