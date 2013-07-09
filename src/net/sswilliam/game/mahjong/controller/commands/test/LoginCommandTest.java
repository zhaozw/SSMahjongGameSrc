package net.sswilliam.game.mahjong.controller.commands.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.ServerMain;
import net.sswilliam.game.mahjong.client.MockClient;
import net.sswilliam.game.mahjong.client.UnitTestBlockHandler;
import net.sswilliam.java.utils.StringByteUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCommandTest {


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
		Thread.currentThread().sleep(10);;
		byte[] data = handler.waitForResponse();
		System.out.println("length "+data.length);
		assertTrue(data.length >=2);
		assertEquals(0, data[0]);
		int response = data[1];
	
		assertEquals(Protocal.LOGIN_SUCCESS, response);
		
		byte[] left = Arrays.copyOfRange(data, 2, data.length);
		String returnValue = StringByteUtils.byte2str(left);
		System.out.println(returnValue);
		String[] datas = returnValue.split(",");
		assertEquals(3, datas.length);
		assertEquals("e5b74328-006f-48a9-8f98-50a84277f6f1", datas[0]);
		assertEquals("Usswilliam", datas[1]);
		assertEquals("sswilliam.jpg", datas[2]);
		
	}
	
	@Test
	public void testLoginWithSameConnectionMoreThanOnec() throws Exception{

		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();
		client.addHandler(handler);

		handler.setFlag(Protocal.LOGIN);
		
		
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);;
		byte[] data = handler.waitForResponse();
		System.out.println("ddddd "+data.length);
		int response = data[1];
		byte[] leftData = Arrays.copyOfRange(data, 1, data.length);
		String info = StringByteUtils.byte2str(leftData);
		System.out.println("dddd "+info);
		assertEquals(Protocal.LOGIN_SUCCESS, response);

		Thread.currentThread().sleep(10);;
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);;
		byte[] data2 = handler.waitForResponse();
		int response2 = data2[1];
		assertEquals(Protocal.LOGIN_CONNECTION_EXITING, response2);

		Thread.currentThread().sleep(10);;
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);;
		byte[] data3 = handler.waitForResponse();
		int response3 = data3[1];
		assertEquals(Protocal.LOGIN_CONNECTION_EXITING, response3);

		Thread.currentThread().sleep(10);;
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);;
		byte[] data4 =handler.waitForResponse();
		int response4 = data4[1];
		assertEquals(Protocal.LOGIN_CONNECTION_EXITING, response4);

//		ServerMain.getInstance().stop();
//		Thread.currentThread().sleep(500);
	}
	
	@Test
	public void testLoginWithFalseUserNameAndPassowrd() throws Exception{
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient client = new MockClient();
		client.connect();
		client.addHandler(handler);

		handler.setFlag(Protocal.LOGIN);
		client.login("sswilliam", "sswilliam2");
		Thread.currentThread().sleep(10);
		byte[] data = handler.waitForResponse();
		int response = data[1];
		assertEquals(Protocal.LOGIN_FAILED, response);


//		ServerMain.getInstance().stop();
//		Thread.currentThread().sleep(500);
	}
	
	@Test
	public void testLoginWithMulitConnectionButSameUserName() throws Exception{
		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		handler.setFlag(Protocal.LOGIN);
		MockClient client = new MockClient();
		client.connect();
		client.addHandler(handler);
		client.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		byte[] data = handler.waitForResponse();
		int response = data[1];
		assertEquals(Protocal.LOGIN_SUCCESS, response);
		
		
//
		UnitTestBlockHandler handler2 = new UnitTestBlockHandler();
		MockClient client2 = new MockClient();
		client2.connect();
		client2.addHandler(handler2);
		handler2.setFlag(Protocal.LOGIN);
		client2.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
//		
		byte[] data2 = handler2.waitForResponse();
		int response2 = data2[1];
		assertEquals(Protocal.LOGIN_USER_EXISTING, response2);

		UnitTestBlockHandler handler3 = new UnitTestBlockHandler();
		MockClient client3 = new MockClient();
		client3.connect();
		client3.addHandler(handler3);
		handler3.setFlag(Protocal.LOGIN);
		client3.login("sswilliam", "sswilliam");
		Thread.currentThread().sleep(10);
		
		byte[] data3 = handler3.waitForResponse();
		int response3 = data3[1];
		assertEquals(Protocal.LOGIN_USER_EXISTING, response3);
		
		
//		ServerMain.getInstance().stop();
//		Thread.currentThread().sleep(500);
	}
	
	
	@Test
	public void testMaxLogin() throws Exception{
		ArrayList<MockClient> clintes = new ArrayList<MockClient>();
		for(int i = 0;i<20;i++){
			UnitTestBlockHandler handler = new UnitTestBlockHandler();
			MockClient client = new MockClient();
			client.connect();
			client.addHandler(handler);
			
			clintes.add(client);
			handler.setFlag(Protocal.LOGIN);
			client.login("sswilliam"+i, "sswilliam"+i);
			Thread.currentThread().sleep(10);
			byte[] data = handler.waitForResponse(); 
			int response = data[1];
			assertEquals(Protocal.LOGIN_SUCCESS, response);
		}

		UnitTestBlockHandler handler = new UnitTestBlockHandler();
		MockClient tobeFailedClient = new MockClient();
		tobeFailedClient.connect();
		tobeFailedClient.addHandler(handler);
		handler.setFlag(Protocal.LOGIN);
		tobeFailedClient.login("sswlliam", "sswlliam");
		Thread.currentThread().sleep(10);
		byte[] data =  handler.waitForResponse();
		int response = data[1];
		assertEquals(Protocal.LOGIN_MAX_USER_REACHED, response);
		

		UnitTestBlockHandler handler2 = new UnitTestBlockHandler();
		MockClient tobeFailedClient2 = new MockClient();
		tobeFailedClient2.connect();
		tobeFailedClient2.addHandler(handler2);
		handler2.setFlag(Protocal.LOGIN);
		tobeFailedClient2.login("sswlliam3", "sswlliam3");
		Thread.currentThread().sleep(10);
		byte[] data2 = handler2.waitForResponse();
		int response2 = data2[1];
		assertEquals(Protocal.LOGIN_MAX_USER_REACHED, response2);

		UnitTestBlockHandler handler3 = new UnitTestBlockHandler();
		MockClient tobeFailedClient3 = new MockClient();
		tobeFailedClient3.connect();
		tobeFailedClient3.addHandler(handler3);
		handler3.setFlag(Protocal.LOGIN);
		tobeFailedClient3.login("sswlliam3", "sswlliam");
		Thread.currentThread().sleep(10);
		byte[] data3 = handler3.waitForResponse();
		int response3 = data3[1];
		assertEquals(Protocal.LOGIN_MAX_USER_REACHED, response3);
		
//		ServerMain.getInstance().stop();
//		Thread.currentThread().sleep(500);
	}
	

}
