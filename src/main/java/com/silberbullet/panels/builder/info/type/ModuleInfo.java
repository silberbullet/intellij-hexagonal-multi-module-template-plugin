package com.silberbullet.panels.builder.info.type;

public record ModuleInfo(
        String locationPath,
        String folderName,
        String packageName,
        String moduleName
) {
    public ModuleInfo(String locationPath, String folderName, String packageName, String moduleName) {
        this.locationPath = locationPath.strip();
        this.folderName = folderName.strip();
        this.packageName = packageName.strip();
        this.moduleName = moduleName.strip();
    }
}
