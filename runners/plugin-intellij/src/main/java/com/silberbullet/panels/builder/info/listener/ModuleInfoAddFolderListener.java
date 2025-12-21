package com.silberbullet.panels.builder.info.listener;

@FunctionalInterface
public interface ModuleInfoAddFolderListener {
    
    void onAddFolderRequested(String location, String folderName);
}
