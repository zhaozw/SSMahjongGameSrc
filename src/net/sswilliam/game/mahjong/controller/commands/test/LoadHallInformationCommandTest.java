package net.sswilliam.game.mahjong.controller.commands.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.ServerMain;
import net.sswilliam.game.mahjong.client.MockClient;
import net.sswilliam.java.utils.StringByteUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoadHallInformationCommandTest {

	
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
	public void testGetHallInfo() throws Exception{
		
		MockClient client = new MockClient();
		client.connect();
		client.login("sswilliam", "sswilliam");
		byte[] bytes = client.loadHallInfo();
		System.out.println("length"+bytes.length);
		assertEquals(Protocal.LOAD_HALL_INFORMATION, bytes[0]);
		assertEquals(Protocal.LOAD_HALL_INFORMATION_SUCCESS, bytes[1]);
		byte[] data = Arrays.copyOfRange(bytes, 2, bytes.length);
		String retStr  = StringByteUtils.byte2str(data);
//		assertEquals(expected, actual);
		System.out.println(retStr);
//		client.login(username, password)
	}

	
	@Test
	public void testGetHallInfoWithoutLogin() throws Exception{
		MockClient client = new MockClient();
		client.connect();
		byte[] bytes = client.loadHallInfo();
		System.out.println("length"+bytes.length);
		assertEquals(Protocal.LOAD_HALL_INFORMATION, bytes[0]);
		assertEquals(Protocal.COMMON_NOT_LOGIN, bytes[1]);
	}
}
