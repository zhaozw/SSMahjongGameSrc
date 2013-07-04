package net.sswilliam.game.mahjong.client.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class UIManager {

	public static LoginFrame login = new LoginFrame();
	public static HallFrame hallFrame = new HallFrame();
	public static TableFrame tableFrame = new TableFrame();
	public static GameFrame gameFrame = new GameFrame();
	
	public static int screenW = -1;
	public static int screenH = -1;
	public static void makeFrameCenter(JFrame frame){
		if(screenW == -1){
			Toolkit kToolkit = Toolkit.getDefaultToolkit();
			screenH = kToolkit.getScreenSize().height;
			screenW = kToolkit.getScreenSize().width;
		}
		if(screenW == 1200 && screenH == 1600){
			frame.setLocation(1200 + (2560 - frame.getWidth())/2, (1440 - frame.getHeight())/2);
		}else{

			frame.setLocation((2560 - frame.getWidth())/2, (1440 - frame.getHeight())/2);
		}
			
	}
	
	public static void showLoginFrame(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				UIManager.login.setVisible(true);
			}
		});
	}
	public static void fadeLoginFrame(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				UIManager.login.setVisible(false);
			}
		});
	}
	
	private static boolean debug = true;
	public static void add(Container parent, JComponent comp, int x, int y, int w, int h){
		comp.setBounds(x, y, w, h);

//		comp.setBorder(new LineBorder(Color.RED));
		
		parent.add(comp);
	}
	
	
}
