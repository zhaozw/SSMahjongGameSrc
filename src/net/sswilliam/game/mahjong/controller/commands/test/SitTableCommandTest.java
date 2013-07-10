package net.sswilliam.game.mahjong.controller.commands.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.ServerMain;
import net.sswilliam.game.mahjong.client.MockClient;
import net.sswilliam.game.mahjong.client.UnitTestBlockHandler;
import net.sswilliam.java.utils.StringByteUtils;

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
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)0, (byte)0);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals(bytes[1], Protocal.SIT_TABLE_SUCCESS);
		
		
		
		
	}
	
	
	@Test
	public void testConnectMoreTimes() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)0, (byte)0);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes[1]);
		
		Thread.sleep(100);
		

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)0, (byte)0);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_NOT_RIGHT_USER_STATE,bytes[1]);
		
		

		Thread.sleep(100);
		

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)1, (byte)0);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_NOT_RIGHT_USER_STATE,bytes[1]);
		


		Thread.sleep(100);
		

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals(Protocal.SIT_TABLE_NOT_RIGHT_USER_STATE,bytes[1]);
		
		

		Thread.sleep(100);
		

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)101, (byte)1);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_NOT_RIGHT_USER_STATE,bytes[1]);
		
		

		Thread.sleep(100);
		

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)1, (byte)5);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_NOT_RIGHT_USER_STATE,bytes[1]);
	}
	
	@Test
	public void testIncorrectTable() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)101, (byte)0);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_TABLE_NOT_EXIST,bytes[1]);
		

		Thread.sleep(100);
		

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes[1]);
		
		
		
		
	}
	
	
	
	@Test
	public void testIncorrectTable2() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)-1, (byte)0);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_TABLE_NOT_EXIST,bytes[1]);
		

		Thread.sleep(100);

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes[1]);
		
		
		
		
	}

	
	
	@Test
	public void testIncorrectTable3() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)101, (byte)4);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_TABLE_NOT_EXIST,bytes[1]);
		

		
		
		
		
	}

	
	@Test
	public void testIncorrectSeat() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)10, (byte)4);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SEAT_NOT_EXIST,bytes[1]);
		

		Thread.sleep(100);

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes[1]);
		
		
		
		
		
	}
	
	
	
	@Test
	public void testIncorrectSeat2() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)10, (byte)-1);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SEAT_NOT_EXIST,bytes[1]);
		

		Thread.sleep(100);

		handler.setFlag(Protocal.SIT_TABLE);
		client.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes[1]);
		
		
	}
	
	
	
	
	@Test
	public void testTakenSeatSencario() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes[1]);
		

		Thread.sleep(100);

	
		
		UnitTestBlockHandler handler2 = new UnitTestBlockHandler();
		MockClient client2 = new MockClient();
		client2.connect();

		client2.addHandler(handler2);
		handler2.setFlag(Protocal.LOGIN);
		client2.login("sswilliam1", "sswilliam1");
		Thread.currentThread().sleep(10);
		handler2.waitForResponse();
		handler2.setFlag(Protocal.SIT_TABLE);
		client2.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		byte[] bytes2 = handler2.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SIT_ALREADY_TAKEN,bytes2[1]);
		

		Thread.sleep(100);
		

		handler2.setFlag(Protocal.SIT_TABLE);
		client2.sit((byte)1, (byte)2);
		Thread.currentThread().sleep(10);
		bytes2 = handler2.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes2[1]);
		
		
		UnitTestBlockHandler handler3 = new UnitTestBlockHandler();
		MockClient client3 = new MockClient();
		client3.connect();

		client3.addHandler(handler3);
		handler3.setFlag(Protocal.LOGIN);
		client3.login("sswilliam1", "sswilliam1");
		Thread.currentThread().sleep(10);
		byte[] loginData = handler3.waitForResponse();

		assertEquals(Protocal.LOGIN, loginData[0]);
		assertEquals(Protocal.LOGIN_USER_EXISTING, loginData[1]);
		

		handler3.setFlag(Protocal.LOGIN);
		client3.login("sswilliam2", "sswilliam2");
		Thread.currentThread().sleep(10);
		loginData = handler3.waitForResponse();

		assertEquals(Protocal.LOGIN, loginData[0]);
		assertEquals(Protocal.LOGIN_SUCCESS, loginData[1]);
		
		handler3.setFlag(Protocal.SIT_TABLE);
		client3.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		byte[] bytes3 = handler3.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SIT_ALREADY_TAKEN,bytes3[1]);
		
		Thread.currentThread().sleep(100);

		handler3.setFlag(Protocal.SIT_TABLE);
		client3.sit((byte)1, (byte)2);
		Thread.currentThread().sleep(10);
		bytes3 = handler3.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SIT_ALREADY_TAKEN,bytes3[1]);
		

		Thread.currentThread().sleep(100);

		handler3.setFlag(Protocal.SIT_TABLE);
		client3.sit((byte)2, (byte)2);
		Thread.currentThread().sleep(10);
		bytes3 = handler3.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes3[1]);
		
	}
	
	
	
	@Test
	public void testTableBroadCast() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		handler.setFlag(Protocal.SIT_TABLE);
		
		
		client.sit((byte)1, (byte)1);
		Thread.currentThread().sleep(10);
		byte[] bytes = handler.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes[1]);
		

		Thread.sleep(100);
		handler.setFlag(Protocal.NOTIFY_SIT_TABLE);

	
		
		UnitTestBlockHandler handler2 = new UnitTestBlockHandler();
		MockClient client2 = new MockClient();
		client2.connect();

		client2.addHandler(handler2);
		handler2.setFlag(Protocal.LOGIN);
		client2.login("sswilliam1", "sswilliam1");
		Thread.currentThread().sleep(10);
		handler2.waitForResponse();
		handler2.setFlag(Protocal.SIT_TABLE);
		client2.sit((byte)1, (byte)2);
		Thread.currentThread().sleep(10);
		byte[] bytes2 = handler2.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes2[1]);
		Thread.sleep(100);
		
		bytes = handler.waitForResponse();
		assertEquals(Protocal.NOTIFY_SIT_TABLE, bytes[0]);
		assertEquals(1, bytes[1]);
		assertEquals(2, bytes[2]);
		byte[] nameBytes = Arrays.copyOfRange(bytes, 3, bytes.length);
		System.out.println(StringByteUtils.byte2str(nameBytes));
		assertEquals("Usswilliam1", StringByteUtils.byte2str(nameBytes));
		
		Thread.currentThread().sleep(100);
		
		handler.setFlag(Protocal.NOTIFY_SIT_TABLE);
		handler2.setFlag(Protocal.NOTIFY_SIT_TABLE);
		
		
		UnitTestBlockHandler handler3 = new UnitTestBlockHandler();
		MockClient client3 = new MockClient();
		client3.connect();

		client3.addHandler(handler3);
		handler3.setFlag(Protocal.LOGIN);
		client3.login("sswilliam2", "sswilliam2");
		Thread.currentThread().sleep(10);
		handler3.waitForResponse();
		handler3.setFlag(Protocal.SIT_TABLE);
		client3.sit((byte)1, (byte)0);
		Thread.currentThread().sleep(10);
		byte[] bytes3 = handler3.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes3[1]);
		Thread.sleep(100);
		
		bytes = handler.waitForResponse();
		
		assertEquals(Protocal.NOTIFY_SIT_TABLE, bytes[0]);
		assertEquals(1, bytes[1]);
		assertEquals(0, bytes[2]);
		
		nameBytes = Arrays.copyOfRange(bytes, 3, bytes.length);
		assertEquals("Usswilliam2", StringByteUtils.byte2str(nameBytes));
		
		
		bytes2 = handler2.waitForResponse();
		assertEquals(Protocal.NOTIFY_SIT_TABLE, bytes2[0]);
		assertEquals(1, bytes2[1]);
		assertEquals(0, bytes2[2]);
		
		nameBytes = Arrays.copyOfRange(bytes2, 3, bytes.length);
		assertEquals("Usswilliam2", StringByteUtils.byte2str(nameBytes));
		
		
		
		
		
		

		
		
	}
	
	
	

	@Test
	public void testHallBroadCast() throws Exception{
		
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();

		client.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		handler.waitForResponse();
		
		
		UnitTestBlockHandler handler2 = new UnitTestBlockHandler();
		MockClient client2 = new MockClient();
		client2.connect();

		client2.addHandler(handler2);
		handler2.setFlag(Protocal.LOGIN);
		client2.login("sswilliam1", "sswilliam1");
		Thread.currentThread().sleep(10);
		handler2.waitForResponse();
		
		
		
		UnitTestBlockHandler handler3 = new UnitTestBlockHandler();
		MockClient client3 = new MockClient();
		client3.connect();

		client3.addHandler(handler3);
		handler3.setFlag(Protocal.LOGIN);
		client3.login("sswilliam2", "sswilliam2");
		Thread.currentThread().sleep(10);
		handler3.waitForResponse();
		
		Thread.currentThread().sleep(100);
		
		handler.setFlag(Protocal.NOTIFY_SIT_TABLE);
		handler2.setFlag(Protocal.NOTIFY_SIT_TABLE);
		
		handler3.setFlag(Protocal.SIT_TABLE);
		client3.sit((byte)1, (byte)0);
		Thread.currentThread().sleep(10);
		byte[] bytes3 = handler3.waitForResponse();
		assertEquals( Protocal.SIT_TABLE_SUCCESS,bytes3[1]);
		Thread.sleep(100);
		
		byte[] bytes = handler.waitForResponse();
		
		assertEquals(Protocal.NOTIFY_SIT_TABLE, bytes[0]);
		assertEquals(1, bytes[1]);
		assertEquals(0, bytes[2]);
		
		byte[] nameBytes = Arrays.copyOfRange(bytes, 3, bytes.length);
		assertEquals("Usswilliam2", StringByteUtils.byte2str(nameBytes));
		
		
		byte[] bytes2 = handler2.waitForResponse();
		assertEquals(Protocal.NOTIFY_SIT_TABLE, bytes2[0]);
		assertEquals(1, bytes2[1]);
		assertEquals(0, bytes2[2]);
		
		nameBytes = Arrays.copyOfRange(bytes2, 3, bytes.length);
		assertEquals("Usswilliam2", StringByteUtils.byte2str(nameBytes));
		
		
		
		
		
		

		
		
	}
	
	
}
