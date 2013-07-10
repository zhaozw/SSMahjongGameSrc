package net.sswilliam.game.mahjong.client.ui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.sswilliam.game.mahjong.client.ClientContext;

public class TableUserInfo extends JPanel {

	private ClientContext context;
	
	public JPanel head;
	public JLabel username;
	public JLabel userHeader;
	public JLabel userState;
	public TableUserInfo(ClientContext context) {
		this.context = context;
		
		userHeader = new JLabel();
		this.setLayout(null);
		userHeader.setBorder(new LineBorder(Color.LIGHT_GRAY));
		userHeader.setAlignmentX(SwingConstants.CENTER);
		userHeader.setAlignmentY(SwingConstants.CENTER);
		userHeader.setIcon(new ImageIcon("asset\\user.png"));
		context.add(this, userHeader, 15, 5, 50, 50);
		
		username = new JLabel();
		context.add(this, username, 5, 55, 70, 20);
		userHeader.setAlignmentX(SwingConstants.CENTER);
		
		userState = new JLabel();
		context.add(this, userState, 5, 75, 70, 20);
		userState.setAlignmentX(SwingConstants.CENTER);
		
		this.setSize(80,95);
		this.setBorder(new LineBorder(Color.DARK_GRAY));
		
		// TODO Auto-generated constructor stub
	}

}
