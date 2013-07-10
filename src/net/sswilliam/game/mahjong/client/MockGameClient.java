package net.sswilliam.game.mahjong.client;

import net.sswilliam.game.mahjong.ServerMain;

public class MockGameClient {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerMain.getInstance().start();
			}
		}, "Server").start();
		
		try {javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");}catch (Exception e) {}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				new ClientContext("[A]",-300, 0,"sswilliam","sswilliam").start();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				new ClientContext("[B]",300, 0, "sswilliam1", "sswilliam1").start();
			}
		}).start();
	}
}
