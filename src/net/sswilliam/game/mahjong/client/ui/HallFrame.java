package net.sswilliam.game.mahjong.client.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sswilliam.game.mahjong.client.model.ServerStub;

public class HallFrame extends JFrame {

	
	public JLabel status = new JLabel();
	
	private JScrollPane tablesScroll;
	private JPanel tableContainer = new JPanel();
	ArrayList<TableInHall> tables = new ArrayList<TableInHall>();
	
	public HallFrame(){
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000 ,600);
		setLocation(100, 100);
		
		status.setForeground(Color.RED);
		UIManager.add(getContentPane(), status, 0, 540, 1000, 20);
		
		tableContainer.setLayout(null);
		for(int i = 0;i<30;i++){
			TableInHall tableInHall = new TableInHall(i);
			tables.add(tableInHall);
			UIManager.add(tableContainer, tableInHall, 10+260*(i%3), 10+260*(i/3), 250, 250);
		}
		

		tableContainer.setPreferredSize(new Dimension(800, 2610));
		tablesScroll = new JScrollPane(tableContainer);
		//tablesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		UIManager.add(getContentPane(), tablesScroll, 0, 0, 810, 530);
		UIManager.makeFrameCenter(this);
		
	}
	public void refresh(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerStub.loadHallInfo();
			}
		}).start();
	}
	public void updateTables(int id, String status){
		if(id>-1 && id<tables.size()){
			TableInHall tableInHall = tables.get(id);
			tableInHall.refreshData(status);
		}
	}
}
