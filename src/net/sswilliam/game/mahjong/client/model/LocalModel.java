package net.sswilliam.game.mahjong.client.model;

import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.game.platform.user.User;

public class LocalModel {

	private User user = new User();
	private String sesionid;
	private ClientContext context;
	public LocalModel(ClientContext context){
		this.context = context;
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
