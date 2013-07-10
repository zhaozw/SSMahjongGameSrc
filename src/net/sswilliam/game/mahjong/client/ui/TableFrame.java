package net.sswilliam.game.mahjong.client.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.sswilliam.game.mahjong.client.ClientContext;

public class TableFrame extends JFrame {

	public TableUserInfo selfInfo;
	public TableUserInfo nextInfo;
	public TableUserInfo preInfo;
	public TableUserInfo againstInfo;
	
	public JLabel status = new JLabel();
	public JLabel quanLable = new JLabel();
	
	public JButton readyBtn = new JButton();
	
	
	private ClientContext context;
	public TableFrame(ClientContext context){
		this.context = context;
		setTitle(context.instanceName+" Table");
		this.getContentPane().setLayout(null);
		this.setSize(816, 638);
		this.setResizable(false);
		
		context.makeFrameCenter(this);
		
		
		selfInfo = new TableUserInfo(context);
		context.add(getContentPane(), selfInfo, 360, 485, 80, 110);
		
		againstInfo = new TableUserInfo(context);
		context.add(getContentPane(), againstInfo, 360, 5, 80, 110);
		
		preInfo = new TableUserInfo(context);
		context.add(getContentPane(), preInfo, 5, 245, 80, 110);
		
		nextInfo = new TableUserInfo(context);
		context.add(getContentPane(), nextInfo, 715, 245, 80, 110);
		
	}
}
