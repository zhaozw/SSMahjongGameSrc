package net.sswilliam.game.mahjong.client.controller;


import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.game.mahjong.client.MockClient;
import net.sswilliam.java.utils.MD5Utils;

public class Controller {

	public MockClient client = new MockClient();
	private SimpleJavaClientResponseHandler handler;

	private ClientContext context;
	public Controller(ClientContext context){
		this.context = context;
		client = new MockClient();
		handler = new SimpleJavaClientResponseHandler(context);
	}
	
	public void login(final String username, final String password) {
		try {

			client.connect();
			client.addHandler(handler);
			Thread.currentThread().sleep(20);
			context.model.getUser().loginname = username;
			context.model.getUser().password = MD5Utils.encrypt(password);
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
								context.login.label.setText(e.getMessage());
								context.login.button.setEnabled(true);
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
					context.login.label.setText(e.getMessage());
					context.login.button.setEnabled(true);
				}
			});
		}

	}

	public  void loadHallInfo() {
		System.out.println("start load");
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				context.hallFrame.status
						.setText("start load hall information");
			}
		});
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
							context.hallFrame.status.setText(e.getMessage());
						}
					});
					// TODO: handle exception
				}
			}
		}).start();
	}

	public void sit(final byte table, final byte seat) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				context.hallFrame.status.setText("start to enter the room");
				context.hallFrame.tablesScroll.setVisible(false);
			}
		});
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					client.sit(table, seat);
				} catch (final Exception e) {
					e.printStackTrace();
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							context.hallFrame.tablesScroll.setVisible(true);
							context.hallFrame.status.setText("sit exception:"+e.getMessage());
						}
					});

				}

			}
		}).start();

	}
}
