package org.example.problems.logger;

import java.util.ArrayList;
import java.util.List;

enum LogLevel{
    INFO, DEBUG, WARN, ERROR
}
class LogMessage{
    String message;
    LogLevel logLevel;
    long timestamp;

    LogMessage(String msg, LogLevel level) {
        this.message = msg;
        this.logLevel = level;
        this.timestamp = System.currentTimeMillis();
    }
}

interface Formatter{
    String format(LogMessage logMessage);
}

class SimpleFormatter implements Formatter{
    public String format(LogMessage logMessage)
    {
        return logMessage.timestamp+" [ "+logMessage.message+" ] ";
    }
}

interface Appender{
    void append(LogMessage logMessage);
}

class ConsoleAppender implements Appender{
    Formatter formatter;

    ConsoleAppender(Formatter formatter)
    {
        this.formatter=formatter;
    }
   public void append(LogMessage logMessage)
    {
        System.out.println(formatter.format(logMessage));
    }
}

class FileAppender implements Appender{
    Formatter formatter;
    String filePath;

    FileAppender(Formatter formatter,String filePath)
    {
        this.formatter=formatter;
        this.filePath=filePath;
    }

   public void append(LogMessage logMessage)
    {
        System.out.println("Written to file");
        //Write to file
    }
}

class Logger{
    private static Logger instance;
    private List<Appender> appenders=new ArrayList<>();
    private LogLevel currentLogLevel;

    private Logger(){}

    public static Logger getInstance()
    {
        if(instance==null)
        {
            synchronized (Logger.class)
            {
                if(instance==null)
                {
                    instance=new Logger();
                }
            }
        }
        return instance;
    }

    public void setLogLevel(LogLevel logLevel)
    {
        this.currentLogLevel=logLevel;
    }

    public void addAppender(Appender appender)
    {
        appenders.add(appender);
    }

    public void log(LogLevel logLevel,String message)
    {
        if(logLevel.ordinal()>=currentLogLevel.ordinal())
        {
            LogMessage msg=new LogMessage(message,logLevel);
            for(Appender appender:appenders)
            {
                appender.append(msg);
            }
        }
    }

}




public class LogManager {

    public static void main(String[] args)
    {
        Logger logger = Logger.getInstance();

        Formatter formatter=new SimpleFormatter();
        Appender console=new ConsoleAppender(formatter);

        logger.addAppender(console);
        logger.setLogLevel(LogLevel.INFO);

        logger.log(LogLevel.INFO, "Application started");
        logger.log(LogLevel.DEBUG, "This won't be printed");
    }

}
