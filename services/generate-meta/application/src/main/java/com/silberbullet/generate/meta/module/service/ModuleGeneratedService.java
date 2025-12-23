package com.silberbullet.generate.meta.module.service;

import com.silberbullet.generate.meta.module.domain.Module;
import com.silberbullet.generate.meta.module.domain.type.ModuleGeneratedFile;
import com.silberbullet.generate.meta.module.usecase.ModuleCreateUseCase;

public class ModuleGeneratedService implements ModuleCreateUseCase {

    @Override
    public ModuleGeneratedFile createModule(Module module) {
        assert module.getFolderName() != null;
        assert module.getLocationPath() != null;
        assert module.getModuleName() != null;

        //


        return null;
    }
}
