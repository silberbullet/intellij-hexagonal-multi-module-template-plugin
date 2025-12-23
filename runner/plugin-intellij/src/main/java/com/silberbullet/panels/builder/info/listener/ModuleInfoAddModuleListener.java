package com.silberbullet.panels.builder.info.listener;

import com.silberbullet.panels.builder.info.type.ModuleInfo;

@FunctionalInterface
public interface ModuleInfoAddModuleListener {
    
    void onModuleRequested(ModuleInfo moduleInfo);
}
