# Requirements for a Logger System:

* Log messages with different severity levels (e.g., DEBUG, INFO, WARN, ERROR)
* Include timestamp, log level, and message in each log entry
* Support logging from different parts of the application (e.g., different classes or modules)
* Allow configuration of minimum log level to be recorded
* Provide a way to output logs to console and/or file

# Qualities and Non-Functional Requirements:

* Performance: Minimal impact on application performance
* Thread-safety: Safe to use in multi-threaded environments
* Flexibility: Easy to extend or modify logging behavior
* Simplicity: Simple API for developers to use
* Configurability: Ability to change logging behavior without code modifications
* Reliability: Ensure no loss of log messages under normal circumstances
* Low coupling: Minimal dependencies on other parts of the system


# Core usecases: 
1. Configure Logger with Sink and MinLogLevel
2. Instantiate a logger with default config
3. Log messages

```java
import java.lang.reflect.Method;
import java.time.LocalDateTime;

class LogMessage {
    String message;
    LocalDateTime dateTime;
    LogContext context;

    @Override
    public String toString() {
        return String.format("%s %s -- %s", dateTime, context, message);
    }
}

class LogContext {
    Object logObject;
    Method method;
    LogLevel level;

    @Override
    public String toString() {
        return String.format("%s %s %s", logObject.getSimpleName(), method.getName(), level);
    }
}

interface Sink{
    Sink getSink();
    void setSink();
    
    void append(Appender appender);
}

interface Appender{
    
}

```