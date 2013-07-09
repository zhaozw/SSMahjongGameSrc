package net.sswilliam.game.mahjong.client.model;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.client.MockClient;
import net.sswilliam.game.mahjong.client.ui.UIManager;
import net.sswilliam.game.mahjong.controller.commands.LoginCommand;
import net.sswilliam.game.platform.user.User;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.MD5Utils;
import net.sswilliam.java.utils.StringByteUtils;

public class ServerStub {

	public static final MockClient client = new MockClient();
	private static SimpleJavaClientResponseHandler handler = new SimpleJavaClientResponseHandler();


	public static void login(final String username, final String password) {
		try {

			client.connect();
			client.addHandler(handler);
			Thread.currentThread().sleep(20);
			LocalModel.instance.getUser().loginname = username;
			LocalModel.instance.getUser().password = MD5Utils.encrypt(password);
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {

						client.login(username, password);
					} catch (final Exception e) {
						e.printStackTrace();
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								UIManager.login.label.setText(e.getMessage());
								UIManager.login.button.setEnabled(true);
							}
						});
						// TODO: handle exception
					}
				}
			}).start();
		} catch (final Exception e) {
			e.printStackTrace();
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					UIManager.login.label.setText(e.getMessage());
					UIManager.login.button.setEnabled(true);
				}
			});
		}

	}

	public static void loadHallInfo() {
		System.out.println("start load");
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				UIManager.hallFrame.status
						.setText("start load hall information");
			}
		});
		try {

			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {

						client.loadHallInfo();
					} catch (final Exception e) {
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								UIManager.hallFrame.status.setText(e
										.getMessage());
							}
						});
						// TODO: handle exception
					}
				}
			}).start();

		} catch (final Exception e) {

		}
	}
}
