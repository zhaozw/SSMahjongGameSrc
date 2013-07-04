package net.sswilliam.game.mahjong.model;

import java.util.ArrayList;

import net.sswilliam.game.mahjong.controller.commands.ICommand;

public class Game implements Runnable {

	private String gameId;
	private ArrayList<Round> rounds;
	private ArrayList<ICommand> receivedCommands;
	private Table table;
	
	public Game(Table table){
		this.table = table;
		this.receivedCommands = new ArrayList<ICommand>();
	}
	
	public ArrayList<Round> initEmptyRounds(){
//		ArrayList<E>
		return null;
	}
	@Override
	public void run() {
//		table
		// TODO Auto-generated method stub
	}
	
	public void save(){
		
	}
	
	
}
