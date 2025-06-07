package com.silberbullet.views.models

import com.silberbullet.views.models.common.TemplateCommonModel
import com.silberbullet.views.models.common.TemplateModel

class TemplateServiceModel(
    val common: TemplateCommonModel = TemplateCommonModel(),
    var parentDirectory: String = "",
    var domainPrefix: String = "",
    var firstPackageName: String = "",
    var methodList: MutableSet<Int> = mutableSetOf(),
    var domainStructure: MutableMap<String, Any> = mutableMapOf(
        "api" to mutableMapOf(
            "domain"    to false,
            "exception" to false,
            "readmodel" to false
        ),
        "application" to false,
        "driving"     to false,
        "driven"      to false
    )
) : TemplateModel