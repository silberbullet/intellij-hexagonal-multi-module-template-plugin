package com.silberbullet.generate.meta.module.domain.type;

import org.springframework.aot.generate.GeneratedFiles;

import java.util.List;

public record ModuleGeneratedFile(
        List<GeneratedFiles> files
) implements ModuleArtifact {
}
