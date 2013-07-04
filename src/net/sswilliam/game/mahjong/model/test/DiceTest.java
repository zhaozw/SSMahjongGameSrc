package net.sswilliam.game.mahjong.model.test;

import static org.junit.Assert.*;

import net.sswilliam.game.mahjong.model.Dice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRandom() {
		Dice dice = new Dice();
		for(int i = 0;i<100;i++){
			int number = dice.jump();
//			System.out.println(number);
			assertTrue(number < 7);
			assertTrue(number > 0);
		}
		
	}
	@Test
	public void testFix(){
		Dice dice = new Dice();
		int[] expected = new int[]{1,
				4,
				5,
				3,
				3,
				6,
				2,
				5,
				5,
				2,
				4,
				1};
		for(int i = 0;i<12;i++){
			int num = dice.jump(i);
			assertEquals(expected[i], num);
		}
	}

}
