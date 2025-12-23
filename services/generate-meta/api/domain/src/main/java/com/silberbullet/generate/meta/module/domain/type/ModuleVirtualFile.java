package com.silberbullet.generate.meta.module.domain.type;

import java.util.List;

public record ModuleVirtualFile(
        List<?> virtualFile
) implements ModuleArtifact {
}
