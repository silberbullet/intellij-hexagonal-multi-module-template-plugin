package com.silberbullet.panels.builder.info.type;

public record DomainOptions(
        boolean apiEnabled,
        boolean domainLayer,
        boolean exceptionLayer,
        boolean readModelLayer,
        boolean applicationLayer,
        boolean drivingAdapter,
        boolean webMvc,
        boolean webFlux,
        boolean drivenAdapter,
        boolean rdb,
        boolean redis
) {
}
