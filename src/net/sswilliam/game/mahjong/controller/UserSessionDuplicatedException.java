package net.sswilliam.game.mahjong.controller;

public class UserSessionDuplicatedException extends Exception {

	public UserSessionDuplicatedException(){
		super("User session already exists");
	}
}
