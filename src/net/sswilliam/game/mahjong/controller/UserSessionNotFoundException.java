package net.sswilliam.game.mahjong.controller;

public class UserSessionNotFoundException extends Exception {

	public UserSessionNotFoundException(){
		super("User session does not exit");
	}
}
