package net.sswilliam.game.mahjong.client.controller;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.java.utils.StringByteUtils;

public class LoginResponseHandler extends ClientResponseHandlerBase {

	public LoginResponseHandler(ClientContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(final byte[] response) {
		// TODO Auto-generated method stub
		try {
			System.out.println(response.length);
			System.out.println(response[0]);
			System.out.println("return type " + response[1]);
			switch (response[1]) {
			case Protocal.LOGIN_CONNECTION_EXITING:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						context.login.label.setText("connection existing");
						context.login.button.setEnabled(true);
					}
				});
				break;
			case Protocal.LOGIN_FAILED:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						context.login.label
								.setText("invidate username and passowrd");
						context.login.button.setEnabled(true);
					}
				});
				break;
			case Protocal.LOGIN_SUCCESS:
				byte[] userInfoBytes = Arrays.copyOfRange(response, 2, response.length);
				String userInfoStr = StringByteUtils.byte2str(userInfoBytes);
				String[] datas = userInfoStr.split(",");
				if (datas.length != 3) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							context.login.label
									.setText("login fail, return data error");
							context.login.button.setEnabled(true);
							// new Thread(arg0, arg1)
						}
					});
					return;
				}
				context.model.getUser().uid = datas[0];
				context.model.getUser().username = datas[1];
				context.model.getUser().head = datas[2];
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						context.login.label.setText("login success");
						context.login.button.setEnabled(false);
						context.login.setVisible(false);
						context.hallFrame.refresh();
						context.hallFrame.setVisible(true);
						context.hallFrame.infoPanelHeadInfo.fillWithUser(context.model.getUser());
//
//						UIManager.hallFrame.userNameLabel
//								.setText(LocalModel.instance.getUser().username);
//						UIManager.hallFrame.loginNameLabel
//								.setText(LocalModel.instance.getUser().loginname);
//						UIManager.hallFrame.headLable
//								.setText(LocalModel.instance.getUser().head);

						// new Thread(arg0, arg1)
					}
				});
				break;
			case Protocal.LOGIN_MAX_USER_REACHED:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						context.login.label.setText("too many people");
						context.login.button.setEnabled(true);
					}
				});
				break;
			case Protocal.LOGIN_USER_EXISTING:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						context.login.label.setText("already loggined");
						context.login.button.setEnabled(true);
					}
				});
			case Protocal.LOGIN_EXCEPTION:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						context.login.label.setText("login exception");
						context.login.button.setEnabled(true);
					}
				});
				break;
			default:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						context.login.label.setText("unknow command");
						context.login.button.setEnabled(true);
					}
				});
				break;
			}
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

}
