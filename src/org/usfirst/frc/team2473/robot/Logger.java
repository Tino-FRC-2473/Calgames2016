package org.usfirst.frc.team2473.robot;
import java.time.*;

public class Logger {
	
	public enum LogLevel{
		Debug,
		Info,
		Error,
	};
	
	public LogLevel logLevel = LogLevel.Error;
	
	public static Logger defaultLogger = null;
	
	public static Logger getInstance() {
		if (defaultLogger == null) {
			defaultLogger = new Logger();
		}
		return defaultLogger;
	}
	
	public void log(LogLevel level, String string) {
		
		//	Only log messages at or above the current log level
		
		if ( level.ordinal() >= logLevel.ordinal() ) {
			final Throwable t = new Throwable();
			final StackTraceElement methodCaller = t.getStackTrace()[1];
			System.out.println("" + (LocalTime.now()) + ": " + methodCaller.getClassName() + " " + methodCaller.getMethodName() + " " + "[" + methodCaller.getLineNumber() + "]: " + string);
		}
	}
	
}
