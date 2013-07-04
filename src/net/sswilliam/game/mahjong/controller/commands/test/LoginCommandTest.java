package net.sswilliam.game.mahjong.controller.commands.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.ServerMain;
import net.sswilliam.game.mahjong.client.MockClient;
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
		
		MockClient client = new MockClient();
		client.connect();
		byte[] data = client.login("sswilliam", "sswilliam");
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
//		serverStarted = false;
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				ServerMain.getInstance().start();
//			}
//		},"Server Thread").start();
//		Thread.currentThread().sleep(500);
		MockClient client = new MockClient();
		client.connect();

		byte[] data = client.login("sswilliam", "sswilliam");
		System.out.println("ddddd "+data.length);
		int response = data[1];
		byte[] leftData = Arrays.copyOfRange(data, 1, data.length);
		String info = StringByteUtils.byte2str(leftData);
		System.out.println("dddd "+info);
		assertEquals(Protocal.LOGIN_SUCCESS, response);

		byte[] data2 = client.login("sswilliam", "sswilliam");
		int response2 = data2[1];
		assertEquals(Protocal.LOGIN_CONNECTION_EXITING, response2);

		byte[] data3 = client.login("sswilliam", "sswilliam");
		int response3 = data3[1];
		assertEquals(Protocal.LOGIN_CONNECTION_EXITING, response3);

		byte[] data4 = client.login("sswilliam", "sswilliam");
		int response4 = data4[1];
		assertEquals(Protocal.LOGIN_CONNECTION_EXITING, response4);

//		ServerMain.getInstance().stop();
//		Thread.currentThread().sleep(500);
	}
	
	@Test
	public void testLoginWithFalseUserNameAndPassowrd() throws Exception{
//		serverStarted = false;
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				ServerMain.getInstance().start();
//			}
//		},"Server Thread").start();
//		Thread.currentThread().sleep(500);
		MockClient client = new MockClient();
		client.connect();
		byte[] data = client.login("sswilliam", "sswilliam2");
		int response = data[1];
		assertEquals(Protocal.LOGIN_FAILED, response);


//		ServerMain.getInstance().stop();
//		Thread.currentThread().sleep(500);
	}
	
	@Test
	public void testLoginWithMulitConnectionButSameUserName() throws Exception{
//		serverStarted = false;
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				ServerMain.getInstance().start();
//			}
//		},"Server Thread").start();
//		Thread.currentThread().sleep(500);
		MockClient client = new MockClient();
		client.connect();
		byte[] data = client.login("sswilliam", "sswilliam");
		int response = data[1];
		assertEquals(Protocal.LOGIN_SUCCESS, response);

		MockClient client2 = new MockClient();
		client2.connect();
		byte[] data2 = client2.login("sswilliam", "sswilliam");
		int response2 = data2[1];
		assertEquals(Protocal.LOGIN_USER_EXISTING, response2);
		
		MockClient client3 = new MockClient();
		client3.connect();
		byte[] data3 = client3.login("sswilliam", "sswilliam");
		int response3 = data3[1];
		assertEquals(Protocal.LOGIN_USER_EXISTING, response3);
		
		
//		ServerMain.getInstance().stop();
//		Thread.currentThread().sleep(500);
	}
	
	
	@Test
	public void testMaxLogin() throws Exception{
//		serverStarted = false;
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				ServerMain.getInstance().start();
//			}
//		},"Server Thread").start();
		ArrayList<MockClient> clintes = new ArrayList<MockClient>();
		for(int i = 0;i<20;i++){
			MockClient client = new MockClient();
			client.connect();
			clintes.add(client);
			byte[] data = client.login("sswilliam"+i, "sswilliam"+i);
			int response = data[1];
			assertEquals(Protocal.LOGIN_SUCCESS, response);
		}
		
		MockClient tobeFailedClient = new MockClient();
		tobeFailedClient.connect();
		byte[] data = tobeFailedClient.login("sswlliam", "sswlliam");
		int response = data[1];
		assertEquals(Protocal.LOGIN_MAX_USER_REACHED, response);
		

		MockClient tobeFailedClient2 = new MockClient();
		tobeFailedClient2.connect();
		byte[] data2 = tobeFailedClient2.login("sswlliam3", "sswlliam3");
		int response2 = data2[1];
		assertEquals(Protocal.LOGIN_MAX_USER_REACHED, response2);

		MockClient tobeFailedClient3 = new MockClient();
		tobeFailedClient3.connect();
		byte[] data3 = tobeFailedClient3.login("sswlliam3", "sswlliam");
		int response3 = data3[1];
		assertEquals(Protocal.LOGIN_MAX_USER_REACHED, response3);
		
//		ServerMain.getInstance().stop();
//		Thread.currentThread().sleep(500);
	}
	

}
