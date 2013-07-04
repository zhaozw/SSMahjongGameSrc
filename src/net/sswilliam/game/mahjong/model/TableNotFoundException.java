package net.sswilliam.game.mahjong.model;

public class TableNotFoundException extends Exception {

	public TableNotFoundException(int id) {
		super("Table not founnd with id: "+id);
		// TODO Auto-generated constructor stub
	}

}
