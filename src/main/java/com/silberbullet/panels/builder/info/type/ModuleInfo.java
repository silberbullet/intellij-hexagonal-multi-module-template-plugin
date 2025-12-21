package com.silberbullet.panels.builder.info.type;

public record ModuleInfo(
        String locationPath,
        String folderName,
        String packageName,
        String moduleName,
        DomainOptions domainOptions
) {
    public ModuleInfo {
        locationPath = locationPath.strip();
        folderName = folderName.strip();
        packageName = packageName.strip();
        moduleName = moduleName.strip();
    }
}
