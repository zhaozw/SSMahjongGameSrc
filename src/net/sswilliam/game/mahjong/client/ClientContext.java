package net.sswilliam.game.mahjong.client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import net.sswilliam.game.mahjong.client.controller.Controller;
import net.sswilliam.game.mahjong.client.model.LocalModel;
import net.sswilliam.game.mahjong.client.ui.HallFrame;
import net.sswilliam.game.mahjong.client.ui.LoginFrame;
import net.sswilliam.game.mahjong.client.ui.TableFrame;

public class ClientContext {

	public LoginFrame login;
	public HallFrame hallFrame;
	public TableFrame tableFrame;
	
	public Controller controller;
	
	public LocalModel model;
	
	private int offsetx;
	private int offsety;
	public String username;
	public String password;

	public String instanceName;
	
	public final Object slock = new Object();
	public ClientContext(String instanceName, int offsetX, int offsetY,String username, String password) {
		
		this.instanceName = instanceName;
		this.offsetx = offsetX;
		this.offsety = offsetY;
		this.username = username;
		this.password = password;
		this.model = new LocalModel(this);
		
		login = new LoginFrame(this);
		hallFrame = new HallFrame(this);
		tableFrame = new TableFrame(this);
		
		controller = new Controller(this);
	}

	
	
	public void add(Container parent, JComponent comp, int x, int y,
			int w, int h) {
		comp.setBounds(x, y, w, h);

//		 comp.setBorder(new LineBorder(Color.RED));

		parent.add(comp);
	}

	public void start() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				login.setVisible(true);
			}
		});
	}
	
	public void makeFrameCenter(JFrame frame){
		Toolkit kToolkit = Toolkit.getDefaultToolkit();
		int screenH = kToolkit.getScreenSize().height;
		int screenW = kToolkit.getScreenSize().width;
		if(screenW == 1200 && screenH == 1600){
			frame.setLocation(1200 + (2560 - frame.getWidth())/2+offsetx, (1440 - frame.getHeight())/2+offsety);
		}else{
			frame.setLocation((2560 - frame.getWidth())/2+offsetx, (1440 - frame.getHeight())/2+offsety);
		}
			
	}

}
