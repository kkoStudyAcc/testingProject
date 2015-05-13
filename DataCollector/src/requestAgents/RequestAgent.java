package requestAgents;


public interface RequestAgent {

	static RequestAgentFactory factory = new RequestAgentFactory();
	String getName();
	String httpString();
	int whichKindOfDevice();
	String getHeight();
	String getWidth();
	public static final int normal = 0;
	public static final int mobile = 1;
	public static final int tablet = 2;
}
