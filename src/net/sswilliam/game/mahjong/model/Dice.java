package net.sswilliam.game.mahjong.model;

import java.util.Random;

public class Dice {

	public int jump(){
		return jump((long)Math.round(Math.random()*100000));
	}
	public int jump(long seed){
		Random random = new Random(seed);
		return 1+random.nextInt(6);
	}
}
