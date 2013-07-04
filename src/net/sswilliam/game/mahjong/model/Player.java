package net.sswilliam.game.mahjong.model;

import net.sswilliam.game.platform.user.User;

public class Player {

	private User userStub;
	private Table sittingDesk;
	public static int SIT = 0;
	public static int READY = 0;
	public static int PLAYING = 0;
	private String playerID;
	private String playerName;
	private Hand hand;
	
	
	public void grab(){
		
	}
	public void pung(){
		
	}
	public void chow(){
		
	}
	public void kong(){
		
	}
	public void flowerReplacement(){
		
	}
	public void hu(){
		
	}
	public void reset(){
		
	}
	public User getUserStub(){
		return this.userStub;
	}
}
