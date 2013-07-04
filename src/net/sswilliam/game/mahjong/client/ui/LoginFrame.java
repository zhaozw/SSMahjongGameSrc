package net.sswilliam.game.mahjong.client.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.client.model.ServerStub;

public class LoginFrame extends JFrame implements ActionListener {

	public JTextField usernameField = new JTextField();
	public JTextField password = new JTextField();
	public JButton button = new JButton();
	public JLabel label = new JLabel();
	
	public LoginFrame(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		setSize(300,150);
		setLocation(300, 300);
		usernameField.setText("sswilliam");
		password.setText("sswilliam");
		UIManager.add(getContentPane(), usernameField, 2, 2, 280, 20);
		UIManager.add(getContentPane(), password, 2, 27, 280, 20);
		UIManager.add(getContentPane(), button, 52, 52, 180, 20);
		UIManager.add(getContentPane(), label, 2, 77, 280, 20);
		button.setText("login");
		button.addActionListener(this);
		UIManager.makeFrameCenter(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					button.setEnabled(false);
					
				}
			});
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ServerStub.login(usernameField.getText(), password.getText());
				}
			}).start();
		}
		
		
	}
	
}
