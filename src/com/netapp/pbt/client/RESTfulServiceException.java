package com.netapp.pbt.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RESTfulServiceException extends Exception implements IsSerializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8243681662076258097L;
	private String message;
	
	public RESTfulServiceException() {
		
	}
	
	public RESTfulServiceException(String message){
		super(message);
		this.message=message;
	}
	
	public RESTfulServiceException(Throwable cause){
		super(cause);
	}
	
	public RESTfulServiceException(String message,Throwable cause){
		super(message, cause);
		this.message=message;
	}
	
	public String getMessage(){
		return message;
	}
	
	

}
