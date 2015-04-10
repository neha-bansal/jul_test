package org.apache;

import java.util.Properties;
import java.util.logging.Logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.LoggerRepositoryExImpl;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RepositorySelector;
import org.apache.logging.julbridge.JULLog4jBridge;


public class App {
	private final static Object repositorySelectorGuard = new Object();
	//final static LoggerRepository repositoryExImpl = new LoggerRepositoryExImpl(LogManager.getLoggerRepository());
	
	public static void mergeLogging() {
		JULLog4jBridge.assimilate();
		
//		LogManager.setRepositorySelector(new RepositorySelector() {
//		
//			public LoggerRepository getLoggerRepository() {
//				return repositoryExImpl;
//			}
//		}, repositorySelectorGuard);
	}   
	
	public static void main( String[] args ) {
		mergeLogging();
			
		Properties loggingProperties = new Properties();
		
		loggingProperties.setProperty("log4j.rootLogger", "WARN,console");
		loggingProperties.setProperty("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
		loggingProperties.setProperty("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
		//loggingProperties.setProperty("log4j.appender.console.layout.ConversionPattern", "%d [%t] %-5p %c - %m%n");
		loggingProperties.setProperty("log4j.logger.test.logger", "WARN");
		
		LogManager.resetConfiguration();
		PropertyConfigurator.configure(loggingProperties);
		
		Logger logger = Logger.getLogger("test.logger");
		
		logger.finest("Finest");
		logger.finer("Finer");
		logger.fine("Fine");
		logger.warning("Warning");
		logger.severe("Severe");
	}
}