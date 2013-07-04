package net.sswilliam.game.mahjong.model;

import java.util.ArrayList;

import net.sswilliam.game.mahjong.controller.UserSession;
import net.sswilliam.game.mahjong.model.exceptions.MaxUserSitInTableException;
import net.sswilliam.game.mahjong.model.exceptions.UserAlreadySitInTableException;
import net.sswilliam.game.platform.user.User;

public class Table {

	public enum TableState{
		WAITING,
		PLAYING
	}
	
	public static final byte DONG = 0;
	public static final byte NAN = 1;
	public static final byte XI = 2;
	public static final byte BEI = 3;
	private int tableId;
	private ArrayList<UserSession> observers;
	private TableState status;
	private UserSession[] sessions;
	
	public Table(int id){
		this.tableId = id;
		status = TableState.WAITING;
		sessions = new UserSession[4];
		
	}
	
	public void empty(){
		
	}
	public UserSession getSession(byte sessionIndex){
		return sessions[sessionIndex];
	}
	public void setSession(byte sessionIndex, UserSession session){
		sessions[sessionIndex] = session;
	}
	public String getUserName(byte sessionIndex){
		UserSession userSession = getSession(sessionIndex);
		if(userSession == null){
			return "#";	
		}
		User user = userSession.getUser();
		if(user == null){
			return "#";
		}
		return user.username;
	}
	public boolean isSeatEmpty(byte seatIndex){
		return sessions[seatIndex] == null;
	}
	public void open(){
		
	}
	public void close(){
		
	}
	public int getStatus(){
		return this.status.ordinal();
	}
	
	

}
