package net.sswilliam.game.mahjong.model;

public class Model {

	private static Model instance = new Model();
	private Hall hall;
	private boolean initialized = false;
	private Model(){
		
	}
	public static Model getInstance(){
		return instance;
	}
	public void init(int maxUser, int tableNum){
		if(initialized)
			return;
		hall = new Hall();
		hall.init(maxUser, tableNum);
	}
	public void finalize(){
		if(initialized){
			hall = null;
			initialized = false;
		}
	}
	public Hall getHall(){
		return hall;
	}
}
