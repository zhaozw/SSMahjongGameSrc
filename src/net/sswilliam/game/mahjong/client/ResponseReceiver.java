package net.sswilliam.game.mahjong.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import net.sswilliam.java.logger.Log;


public class ResponseReceiver extends Thread {

	public final Object sLock = new Object();
	private ArrayList<IResponseHandler> handlers = new ArrayList<IResponseHandler>();
	private BufferedInputStream in;
	public ResponseReceiver(BufferedInputStream in){
		this.in =  in;
	}
	public void addHandler(IResponseHandler handler){
		synchronized (sLock) {
			handlers.add(handler);
		}
	}
	private boolean running = false;
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		
		super.start();
		running = true;
	}
	public void tryToStop(){
		this.running = false;
	}
	@Override
	public void run() {
		while(running){
			try {
				final byte[] response = readBytes(in);
				
//				System.out.println("read");
				synchronized (sLock) {
//					System.out.println("handler number:"+handlers.size());
					for(int i = 0;i<handlers.size();i++){
						final IResponseHandler handler = handlers.get(i);
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
//								System.out.println("dispatch to handler");
								handler.handle(response);
							}
						}).start();
					}
				}
				Thread.currentThread().sleep(10);
			}catch (IOException e){
				Log.Excption(e);
			}catch (Exception e) {
				Log.Excption(e);
			}
		}
	}
	public byte[] readBytes(BufferedInputStream in) throws IOException, Exception{
		byte[] bytes = new byte[4096];
		int len = in.read(bytes);
		if(len < 0){
//			System.out.println("length is less than zero");
			return new byte[0];
		}
		if(len > 4096){
			throw new Exception("out of buffer");
		}
		byte[] data = Arrays.copyOf(bytes, len);
		return data;
	}
}
