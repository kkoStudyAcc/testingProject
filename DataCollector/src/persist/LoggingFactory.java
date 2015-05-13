package persist;

public class LoggingFactory {

	public IPersits getConsoleLogging(){
		return new ConsoleLogging();	
	}
	public IPersits getFileLogging(){
		return new FileLogging();
	}
}
