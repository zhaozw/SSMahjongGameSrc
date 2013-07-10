package net.sswilliam.game.mahjong.client.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.game.platform.user.User;

public class HallUserInfo extends JPanel {

	public JLabel loginnameLabel = new JLabel();
	public JLabel usernameLabel = new JLabel();
	public JLabel headLable = new JLabel();
	public JTextField loginnameField = new JTextField();
	public JTextField usernameField = new JTextField();
	public JTextField headField = new JTextField();
	private ClientContext context;
	public HallUserInfo(ClientContext context, String title){
		this.context =context;
		this.setLayout(null);
		this.setSize(180, 170);
		this.setBorder(new TitledBorder(title));
		
		loginnameLabel.setText("Login Name:");
		this.context.add(this, loginnameLabel, 5, 10, 170, 20);
		
		loginnameField.setEnabled(false);
		this.context.add(this, loginnameField, 15, 35, 160, 20);
		

		usernameLabel.setText("Nick Name:");
		this.context.add(this, usernameLabel, 5, 60, 170, 20);
		
		usernameField.setEnabled(false);
		this.context.add(this, usernameField, 15, 85, 160, 20);

		headLable.setText("Head URL:");
		this.context.add(this, headLable, 5, 110, 170, 20);
		
		headField.setEnabled(false);
		this.context.add(this, headField, 15, 135, 160, 20);
		
		
	}
	public void fillWithUser(User user){
		loginnameField.setText(user.loginname);
		usernameField.setText(user.username);
		headField.setText(user.head);
	}
}
