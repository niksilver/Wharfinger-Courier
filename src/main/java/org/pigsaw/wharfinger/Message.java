package org.pigsaw.wharfinger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Message {
	
	@Persistent
	private String message;
	
	@PrimaryKey
	@Persistent
	private String key = "1234";
	
	public Message() {
		this.message = "Initial";
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String m) {
		this.message = m;
	}
}
