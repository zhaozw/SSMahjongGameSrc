package net.sswilliam.game.mahjong.client.model;

import java.io.BufferedInputStream;
import java.util.ArrayList;

import org.xsocket.connection.IHandler;

public class ResponseReceiver extends Thread {

	public final Object sLock = new Object();
	private ArrayList<IHandler> handlers = new ArrayList<IHandler>();
	private BufferedInputStream bin;
	public ResponseReceiver(BufferedInputStream in){
		
	}
	public void addHandler(IHandler handler){
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
			
		}
	}
}
