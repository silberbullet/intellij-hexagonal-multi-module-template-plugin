package com.silberbullet.generate.meta.module.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Module {

    private String moduleId;
    private String projectId;

    private String folderName;
    private String moduleName;
    private String locationPath;
    private String basePackageName;

    private List<String> dependencyModuleNames;

    // TODO 모듈 타입 고려?
}
