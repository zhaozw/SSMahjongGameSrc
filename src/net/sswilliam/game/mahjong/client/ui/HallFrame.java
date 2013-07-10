package net.sswilliam.game.mahjong.client.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.game.mahjong.client.controller.Controller;

public class HallFrame extends JFrame {

	private ClientContext context;
	
	public JLabel status = new JLabel();
	
	
	public JScrollPane tablesScroll;
	public JPanel tableContainer = new JPanel();
	ArrayList<TableInHall> tables = new ArrayList<TableInHall>();
	
	public HallUserInfo infoPanelHeadInfo;
	
//	public JPanel maskJPanel = new JPanel();
	
	
	public HallFrame(ClientContext context){
		this.context = context;
		setTitle(context.instanceName+" Hall");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000 ,600);
		setLocation(100, 100);
		setResizable(false);
		
//		UIManager.add(getContentPane(), maskJPanel, 0, 0, 1000, 600);
//		maskJPanel.setOpaque(true);
//		maskJPanel.setVisible(false);
		
		infoPanelHeadInfo = new HallUserInfo(this.context, "User Information");
		
		status.setForeground(Color.RED);
		this.context .add(getContentPane(), status, 0, 540, 1000, 20);
		
		tableContainer.setLayout(null);
		for(int i = 0;i<30;i++){
			TableInHall tableInHall = new TableInHall(this.context, i);
			tables.add(tableInHall);
			this.context .add(tableContainer, tableInHall, 10+260*(i%3), 10+260*(i/3), 250, 250);
		}
		

		tableContainer.setPreferredSize(new Dimension(790, 2610));
		tablesScroll = new JScrollPane(tableContainer);
		//tablesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.context .add(getContentPane(), tablesScroll, 0, 0, 810, 530);
		this.context .makeFrameCenter(this);
		
		this.context .add(getContentPane(), infoPanelHeadInfo, 812, 10, 180, 170);
		
		
	}
	public void refresh(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				context.controller.loadHallInfo();
			}
		}).start();
	}
	
	public void sit(byte tableid, byte seatid, String username){
		if(tableid>-1 && tableid<tables.size()){
			TableInHall tableInHall = tables.get(tableid);
			tableInHall.sitUser(seatid, username);
		}
	}


	public void updateTables(int id, String status){
		if(id>-1 && id<tables.size()){
			TableInHall tableInHall = tables.get(id);
			tableInHall.refreshData(status);
		}
	}
}
