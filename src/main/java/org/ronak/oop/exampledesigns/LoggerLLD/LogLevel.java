package org.ronak.oop.exampledesigns.LoggerLLD;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LogLevel {

    DEBUG(null),
    INFO(DEBUG),
    WARN(INFO),
    ERROR(WARN),
    OFF(null);

    public final LogLevel parentLevel;
}