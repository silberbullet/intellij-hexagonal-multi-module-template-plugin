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
            "domain" to false,
            "exception" to false,
            "readmodel" to false
        ),
        "application" to false,
        "driving" to mutableMapOf(
            "web-mvc" to false,
            "web-flux" to false,
        ),
        "driven" to mutableMapOf(
            "rdb" to false,
            "redis" to false,
            "client" to false,
        )
    )
) : TemplateModel