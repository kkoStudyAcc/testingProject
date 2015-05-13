package requestAgents;

public class GenericAgent implements RequestAgent {

	private String name = "";
	private String httpString = "";
	private int kindOfDevice = -1;
	private int viewWidth =0;
	private int viewHeight =0;
	
	public GenericAgent(String name, String httpString,int kindOfDevice, int width, int height) {
		super();
		this.name = name;
		this.httpString = httpString;
		this.kindOfDevice = kindOfDevice;
		this.viewHeight = height;
		this.viewWidth = width;
	}
	
	@Override
	public String getName() {
		
		return name;
	}
	@Override
	public String httpString() {
		
		return httpString;
	}
	@Override
	public String toString(){
		return this.name;
	}

	@Override
	public int whichKindOfDevice() {
		
		return this.kindOfDevice;
	}

	@Override
	public String getHeight() {
		return this.viewHeight + "";
	}

	@Override
	public String getWidth() {
		return this.viewWidth + "";
	}
	
}
