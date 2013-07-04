package net.sswilliam.game.mahjong.controller;

import net.sswilliam.game.platform.user.User;

import org.xsocket.connection.INonBlockingConnection;

public class UserSession {

	public enum UserState{
		FREE,
		SITTING,
		READY,
		PLAYING,
	}
	private INonBlockingConnection connection;
	private String sessionid;
	private User user;
	private UserState state;
	
	
	public UserSession(String sessionid, INonBlockingConnection connection, User user){
		this.sessionid = sessionid;
		this.connection = connection;
		this.user = user;
		state = UserState.FREE;
	}
	
	@Override
	public int hashCode() {
		return sessionid.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(obj instanceof UserSession){
			UserSession targetSession = (UserSession)obj;
			return this.sessionid.equals(targetSession.sessionid) && (this.connection == targetSession.connection);
		}
		return false;
	}
	
	public INonBlockingConnection getConnection(){
		return connection;
	}
	public String getSessionID(){
		return sessionid;
	}
	public String getUserId(){
		return this.user.uid;
	}
	public User getUser(){
		return this.user;
	}
	public UserState getState(){
		return this.state;
	}
	public void sit(){
			this.state = UserState.SITTING;
	}
	public void free(){
			this.state = UserState.FREE;
		
	}
	public void play(){
			this.state = UserState.PLAYING;
		
	}
	public void ready(){
			this.state = UserState.READY;
		
	}
	
}
