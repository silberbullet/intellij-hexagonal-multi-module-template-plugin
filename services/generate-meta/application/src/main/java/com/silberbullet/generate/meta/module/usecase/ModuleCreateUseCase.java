package com.silberbullet.generate.meta.module.usecase;

import com.silberbullet.generate.meta.module.domain.Module;
import com.silberbullet.generate.meta.module.domain.type.ModuleArtifact;

public interface ModuleCreateUseCase {

    ModuleArtifact createModule(Module module);
}
