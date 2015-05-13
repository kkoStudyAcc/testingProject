package resultXML;

public class Result {
	public String time;
	public String price;
	public String name;
	public String url;
	public String cssPath;
	public String Useragent;
	public String referer;
	public String toString(){
		return this.name +" : "+this.price +" : "+this.time +" : "+ this.url +" : "+this.cssPath +" : "+ this.Useragent +" : "+ this.referer;
	}
}
