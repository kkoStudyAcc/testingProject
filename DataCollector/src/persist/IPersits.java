package persist;



public interface IPersits {

	public LoggingFactory factory = new LoggingFactory();
	
	public boolean setUpNewTest(String IP);
	public boolean addRequestData(String URL, double price );
	public boolean close(); // persist all data on the disk
}
