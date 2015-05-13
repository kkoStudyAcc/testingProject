package requestObject;

import java.util.ArrayList;

import requestAgents.RequestAgent;


public class RequestObjectFactory {

	public RequestObject createRequestObjecti(String name,String URL, ArrayList<String> cssPaths,String Referer, RequestAgent agent){
		return new RequestObjectImpl(name,URL,cssPaths,Referer,agent);
	}
	
	public RequestObject createRequestObjecti(boolean isMulitRequest,String name,String URL, ArrayList<String> cssPaths,String kind,String Referer, RequestAgent agent){
		if(isMulitRequest){
			return new SingleRequestMuliOutput(name, URL, kind, Referer, agent);
		}else{
			return new RequestObjectImpl(name,URL,cssPaths,Referer,agent);
		}
		/*
		if(cssPaths == null && kind != null){
			return new SingleRequestMuliOutput(name, URL, kind, Referer, agent);
		}
		if(cssPaths != null && kind == null){
			return new RequestObjectImpl(name,URL,cssPaths,Referer,agent);
		}
		return null;
		*/
	}
		
}
