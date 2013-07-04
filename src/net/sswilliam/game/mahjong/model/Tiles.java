package net.sswilliam.game.mahjong.model;

import java.util.ArrayList;

public class Tiles {

	public static final int CHARACTERS = 0;
	public static final int DOTS = 1;
	public static final int BAMBOO = 2;
	public static final int WIND = 3;
	public static final int DRAGONS = 4;
	public static final int FLOWERS = 5;
	
	public static final String[] CHARACTERS_NAME = new String[]{
		"一万","二万","三万","四万","五万","六万","七万","八万","九万"
	};

	public static final String[] DOTS_NAME = new String[]{
		"一饼","二饼","三饼","四饼","五饼","六饼","七饼","八饼","九饼"
	};
	
	public static final String[] BAMBOO_NAME = new String[]{
		"一条","二条","三条","四条","五条","六条","七条","八条","九条"
	};

	public static final String[] WIND_NAME = new String[]{
		"东风","南风","西风","北风",
	};
	
	public static final String[] DRAGONS_NAME = new String[]{
		"中","发","白"
	};
	public static final String[] FLOWERS_NAME = new String[]{
		"春","夏","秋","冬","梅","兰","竹","菊"
	};
	
	
	private int type;
	private int index;
	
	public Tiles(int type, int index){
		this.type = type;
		this.index = index;
	}
	
	public static ArrayList<Tiles> TAILS = new ArrayList<Tiles>();
	static{
		for(int i = 0;i<4;i++){
			for(int j = 0;j<9;j++){
				TAILS.add(new Tiles(CHARACTERS, j));
				TAILS.add(new Tiles(DOTS, j));
				TAILS.add(new Tiles(BAMBOO, j));
			}
			for(int j = 0;j<4;j++){
				TAILS.add(new Tiles(WIND,j));
			}
			for(int j = 0;j<3;j++){
				TAILS.add(new Tiles(DRAGONS,j));
			}
		}
		for(int i = 0;i<8;i++){
			TAILS.add(new Tiles(FLOWERS,i));
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		switch (type) {
		case CHARACTERS:
			return CHARACTERS_NAME[this.index];
		case DOTS:
			return DOTS_NAME[this.index];
		case BAMBOO:
			return BAMBOO_NAME[this.index];
		case WIND:
			return WIND_NAME[this.index];
		case DRAGONS:
			return DRAGONS_NAME[this.index];
		case FLOWERS:
			return FLOWERS_NAME[this.index];
		default:
			return "un supported tiles";
		}
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return type*10+index;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Tiles){
			Tiles target = (Tiles)obj;
			if((target.type == this.type) && (target.index == this.index)){
				return true;
			}
		}
		return false;
	}
	
	public static Tiles createFromHash(int hashcode){
		int type = hashcode/10;
		int index = hashcode%10;
		return new Tiles(type, index);
	}

	
}
