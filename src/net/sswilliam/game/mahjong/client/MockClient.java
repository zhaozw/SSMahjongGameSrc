package net.sswilliam.game.mahjong.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Handler;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.utils.ByteBuffer;
import net.sswilliam.java.utils.MD5Utils;
import net.sswilliam.java.utils.StringByteUtils;

public class MockClient {

	enum ClientState{
		
	}
	public static final String ip = "127.0.0.1";
	public static final int port = 6501;
	private BufferedInputStream in;
	private BufferedReader reader;
	private BufferedOutputStream out;
	private Socket socket;
	private ResponseReceiver receiver;
	public MockClient(){
	}
	private boolean isConnected = false;
	public void connect() throws IOException{
		socket = new Socket(ip, port);
		in = new BufferedInputStream(socket.getInputStream());
		out = new BufferedOutputStream(socket.getOutputStream());
		receiver = new ResponseReceiver(in);
		receiver.start();
	}
	public void addHandler(IResponseHandler handler){
		if(receiver == null){
			throw new RuntimeException("can't add handler before connecting to the server");
		}
		receiver.addHandler(handler);
	}
	public void login(final String username, final String password) throws IOException{
		ByteBuffer buffer = new ByteBuffer();
		buffer.append(Protocal.LOGIN);
		buffer.append(username+',');
		buffer.append(MD5Utils.encrypt(password));
		byte[] data = buffer.toBytes();
		System.out.println("data length"+data.length);
		out.write(data);
		out.flush();
		
		
	}
	public void loadHallInfo() throws IOException{
		byte[] bytes = new byte[1];
		bytes[0] = Protocal.LOAD_HALL_INFORMATION;
		out.write(bytes);
		out.flush();
	}
	public void sit(byte table, byte seat) throws IOException{
		byte[] bytes = new byte[3];
		bytes[0] = Protocal.SIT_TABLE;
		bytes[1] = table;
		bytes[2] = seat;
		out.write(bytes);
		out.flush();
	}
	
	
	
	
	public void mockFlashPrlicy(){
	}
}
