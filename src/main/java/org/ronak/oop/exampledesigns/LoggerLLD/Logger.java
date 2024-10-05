package org.ronak.oop.exampledesigns.LoggerLLD;


/**
 *
 *
 */
public interface Logger {

    Logger getInstance();
    LogConfig getConfig();
    void setConfig(LogConfig config);
    void log(String message);
    void log(LogLevel level, String message);

//     Naive implementation but more convenient. As we add more levels, interface needs to change
//     TODO can we do both?
//    void info(String message);
//    void warn(String message);
//    void error(String message);
//    void debug(String message);


}
