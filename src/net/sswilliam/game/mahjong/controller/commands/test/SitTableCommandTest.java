package net.sswilliam.game.mahjong.controller.commands.test;

import static org.junit.Assert.*;
import net.sswilliam.game.mahjong.ServerMain;
import net.sswilliam.game.mahjong.client.MockClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SitTableCommandTest {

	public static boolean serverStarted = false;
	
	
	@Before
	public void setUp() throws Exception {
		serverStarted = false;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerMain.getInstance().start();
			}
		},"Server Thread").start();

		Thread.currentThread().sleep(500);
	}

	@After
	public void tearDown() throws Exception {

		ServerMain.getInstance().stop();
		Thread.currentThread().sleep(500);
	}
	
	
	@Test
	public void test() throws Exception{
		
		MockClient client = new MockClient();
		client.connect();
		client.login("sswilliam", "sswilliam");
		byte[] ret = client.sit((byte)0, (byte)0);
		assertEquals(2, ret.length);
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//		}).start();;
		
		
	}

	
	
}
