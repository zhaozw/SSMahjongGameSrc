package net.sswilliam.game.mahjong.client.model;

import net.sswilliam.game.platform.user.User;

public class LocalModel {

	public static final LocalModel instance = new LocalModel();
	private User user;
	private String sesionid;
	private LocalModel(){
		
	}
	
	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return this.user;
	}
	public String getSessionId(){
		return sesionid;
	}
	public void setSessionId(String sessionid){
		this.sesionid = sessionid;
	}
}
