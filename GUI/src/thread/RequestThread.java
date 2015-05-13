package thread;

import requestObject.RequestObject;

public class RequestThread implements Runnable {

	Thread t;
	String name;
	RequestObject req ;
	public RequestThread(String name, RequestObject req){
		this.name = name;
		this.req = req;
	}
	public void run() {
		this.req.perform();
	}
	public void start(){
		 System.out.println("Starting " +  name );
	      if (t == null)
	      {
	         t = new Thread (this, name);;
	         t.start ();
	      }
	}

}
