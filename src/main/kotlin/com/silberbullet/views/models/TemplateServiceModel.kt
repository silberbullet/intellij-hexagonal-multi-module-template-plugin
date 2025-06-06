package com.silberbullet.views.models

import com.silberbullet.views.models.common.TemplateCommonModel

data class TemplateServiceModel(
    val common: TemplateCommonModel = TemplateCommonModel(),
    var parentDirectory: String = "",
    var domainPrefix: String = "",
    var firstPackageName: String = "",
    var methodList: MutableSet<Int> = mutableSetOf(),
)