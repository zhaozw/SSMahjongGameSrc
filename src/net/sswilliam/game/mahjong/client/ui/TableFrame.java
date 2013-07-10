package net.sswilliam.game.mahjong.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.AbstractDocument.Content;

import net.sswilliam.game.mahjong.client.ClientContext;

public class TableFrame extends JFrame implements ActionListener {


	public TableUserInfo selfInfo;
	public TableUserInfo nextInfo;
	public TableUserInfo preInfo;
	public TableUserInfo againstInfo;
	
	public JLabel status;
	public JLabel quanLable;
	
	public JButton readyBtn = new JButton();
	
	public byte selfSeat = -1;
	public byte tableid = -1;
	public tableState state = tableState.init;
	
	private ClientContext context;
	public TableFrame(ClientContext context){
		this.context = context;
		setTitle(context.instanceName+" Table");
		this.getContentPane().setLayout(null);
		this.setSize(816, 638);
		this.setResizable(false);
		
		context.makeFrameCenter(this);
		
		
		selfInfo = new TableUserInfo(context);
		context.add(getContentPane(), selfInfo, 360, 510, 80, 95);
		
		againstInfo = new TableUserInfo(context);
		context.add(getContentPane(), againstInfo, 360, 5, 80, 95);
		
		preInfo = new TableUserInfo(context);
		context.add(getContentPane(), preInfo, 5, 252, 80,95);
		
		nextInfo = new TableUserInfo(context);
		context.add(getContentPane(), nextInfo, 725, 252, 80, 95);
		
		status = new JLabel();
		context.add(getContentPane(), status, 0, 580, 800, 20);
		
		quanLable = new JLabel();
		context.add(getContentPane(), quanLable, 30, 20, 50, 50);
		
		readyBtn = new JButton();
		readyBtn.setText("Ready");
		readyBtn.addActionListener(this);
		context.add(getContentPane(), readyBtn, 375, 285, 50, 30);
		
		
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == readyBtn){
			
		}
	}
	
	public void sit(byte seat, String content){
		
	}
	public void init(){
		selfInfo.username.setText("");
		selfInfo.userState.setText("");
		againstInfo.username.setText("");
		againstInfo.userState.setText("");
		preInfo.username.setText("");
		preInfo.userState.setText("");
		nextInfo.username.setText("");
		nextInfo.userState.setText("");
		status.setText("");
		readyBtn.setVisible(true);
	}
	
	public enum tableState{
		init
	}
}
