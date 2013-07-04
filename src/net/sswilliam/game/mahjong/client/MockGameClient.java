package net.sswilliam.game.mahjong.client;

import net.sswilliam.game.mahjong.ServerMain;
import net.sswilliam.game.mahjong.client.ui.UIManager;

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
		UIManager.showLoginFrame();
	}
}
