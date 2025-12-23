package com.silberbullet.panels.builder.preview;

import com.silberbullet.panels.builder.info.type.ModuleInfo;

import java.util.ArrayList;
import java.util.List;

public class PreviewModule {
    
    private final ModuleInfo moduleInfo;
    private final List<String> dependencies = new ArrayList<>();
    
    public PreviewModule(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }
    
    public ModuleInfo getModuleInfo() {
        return moduleInfo;
    }
    
    public List<String> getDependencies() {
        return dependencies;
    }
    
    public void addDependency(String moduleName) {
        dependencies.add(moduleName);
    }
}
