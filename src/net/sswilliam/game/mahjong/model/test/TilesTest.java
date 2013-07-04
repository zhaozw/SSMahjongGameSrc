package net.sswilliam.game.mahjong.model.test;

import static org.junit.Assert.*;

import net.sswilliam.game.mahjong.model.Tiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TilesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		assertEquals(144, Tiles.TAILS.size());
	}

}
