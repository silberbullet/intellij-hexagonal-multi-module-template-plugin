package com.silberbullet.generate.meta.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private String projectId;

    private String projectName;

    private String projectVersion;
}
