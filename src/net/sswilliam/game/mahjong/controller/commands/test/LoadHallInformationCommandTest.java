package net.sswilliam.game.mahjong.controller.commands.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.logging.Handler;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.ServerMain;
import net.sswilliam.game.mahjong.client.MockClient;
import net.sswilliam.game.mahjong.client.UnitTestBlockHandler;
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
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		handler.setFlag(Protocal.LOAD_HALL_INFORMATION);
		MockClient client = new MockClient();
		client.connect();
		
		client.addHandler(handler);
		
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(100);
		client.loadHallInfo();
		Thread.currentThread().sleep(100);
		byte[] bytes = handler.waitForResponse();
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
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		handler.setFlag(Protocal.LOAD_HALL_INFORMATION);
		client.connect();
		client.addHandler(handler);
		client.loadHallInfo();
		byte[] bytes  = handler.waitForResponse();
		System.out.println("length"+bytes.length);
		assertEquals(Protocal.LOAD_HALL_INFORMATION, bytes[0]);
		assertEquals(Protocal.COMMON_NOT_LOGIN, bytes[1]);
	}
	
	@Test
	public void testGetHallInforForSeveralSitting() throws Exception{
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		handler.setFlag(Protocal.LOGIN);
		client.connect();
		client.addHandler(handler);
		client.login("sswilliam", "sswilliam");
		Thread.sleep(100);
		handler.waitForResponse();
		Thread.sleep(100);
		handler.setFlag(Protocal.LOAD_HALL_INFORMATION);
		client.loadHallInfo();
		Thread.sleep(100);
		byte[] hallData = handler.waitForResponse();
		assertEquals(Protocal.LOAD_HALL_INFORMATION, hallData[0]);
		assertEquals(Protocal.LOAD_HALL_INFORMATION_SUCCESS, hallData[1]);
		byte[] hallInfo = Arrays.copyOfRange(hallData, 2, hallData.length);
		String retStr  = StringByteUtils.byte2str(hallInfo);
		System.out.println(retStr);
		
		for(int i = 1;i<19;i++){
			UnitTestBlockHandler thandler = new UnitTestBlockHandler();
			MockClient tclient = new MockClient();
			thandler.setFlag(Protocal.LOGIN);
			tclient.connect();
			tclient.addHandler(thandler);
			tclient.login("sswilliam"+i, "sswilliam"+i);
			Thread.sleep(100);
			thandler.waitForResponse();
			thandler.setFlag(Protocal.SIT_TABLE);
			tclient.sit((byte)i, (byte)(i%4));
			Thread.sleep(100);
			byte[] sitData = thandler.waitForResponse();
			assertEquals(Protocal.SIT_TABLE, sitData[0]);
			assertEquals(Protocal.SIT_TABLE_SUCCESS, sitData[1]);
			
			handler.setFlag(Protocal.LOAD_HALL_INFORMATION);
			client.loadHallInfo();
			Thread.sleep(100);
			hallData = handler.waitForResponse();
			assertEquals(Protocal.LOAD_HALL_INFORMATION, hallData[0]);
			assertEquals(Protocal.LOAD_HALL_INFORMATION_SUCCESS, hallData[1]);

			hallInfo = Arrays.copyOfRange(hallData, 2, hallData.length);
			retStr  = StringByteUtils.byte2str(hallInfo);
			System.out.println(retStr);
		}
	}
}
