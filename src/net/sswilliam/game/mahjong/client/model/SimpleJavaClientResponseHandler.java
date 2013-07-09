package net.sswilliam.game.mahjong.client.model;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.client.IResponseHandler;
import net.sswilliam.game.mahjong.client.ui.UIManager;
import net.sswilliam.game.platform.user.User;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.MD5Utils;
import net.sswilliam.java.utils.StringByteUtils;

public class SimpleJavaClientResponseHandler implements IResponseHandler {

	@Override
	public void handle(byte[] response) {
		// TODO Auto-generated method stub

		if (response != null && response.length > 1) {
			switch (response[0]) {
			case Protocal.LOGIN:
				handleLogin(response);
				break;

			case Protocal.LOAD_HALL_INFORMATION:
				handleLoadHallInfomation(response);
				break;
			default:
				break;
			}
		}

	}
	
	public void handleLoadHallInfomation(final byte[] data){
		
		switch (data[1]) {
		case Protocal.LOAD_HALL_INFORMATION_FAILED:
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					UIManager.hallFrame.status.setText("read data failed");

				}
			});

			break;
		case Protocal.COMMON_NOT_LOGIN:
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					UIManager.hallFrame.status.setText("need login first");

				}
			});
			break;
		case Protocal.LOAD_HALL_INFORMATION_SUCCESS:

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					UIManager.hallFrame.status.setText("load Data success");
					byte[] infoData = Arrays.copyOfRange(data, 2, data.length);
					try {

						String retStr  = StringByteUtils.byte2str(infoData);
						String[] eachTable = retStr.split(":");
						System.out.println(eachTable.length);
						for(int i=0;i<eachTable.length;i++){
							UIManager.hallFrame.updateTables(i, eachTable[i]);
						}
						Log.Debug(retStr, this);
					} catch (Exception e) {

						UIManager.hallFrame.status.setText("Error "+e.getMessage());
						// TODO: handle exception
					}
				}
			});
			break;
		default:
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					UIManager.hallFrame.status
							.setText("not retrieve hall information response");

				}
			});
			break;
		}
	}

	public void handleLogin(byte[] data) {
		try {
			System.out.println(data.length);
			System.out.println(data[0]);
			System.out.println("return type " + data[1]);
			switch (data[1]) {
			case Protocal.LOGIN_CONNECTION_EXITING:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						UIManager.login.label.setText("connection existing");
						UIManager.login.button.setEnabled(true);
					}
				});
				break;
			case Protocal.LOGIN_FAILED:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						UIManager.login.label
								.setText("invidate username and passowrd");
						UIManager.login.button.setEnabled(true);
					}
				});
				break;
			case Protocal.LOGIN_SUCCESS:
				byte[] userInfoBytes = Arrays.copyOfRange(data, 2, data.length);
				String userInfoStr = StringByteUtils.byte2str(userInfoBytes);
				String[] datas = userInfoStr.split(",");
				if (datas.length != 3) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							UIManager.login.label
									.setText("login fail, return data error");
							UIManager.login.button.setEnabled(true);
							// new Thread(arg0, arg1)
						}
					});
					return;
				}
				LocalModel.instance.getUser().uid = datas[0];
				LocalModel.instance.getUser().username = datas[1];
				LocalModel.instance.getUser().head = datas[2];
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						UIManager.login.label.setText("login success");
						UIManager.login.button.setEnabled(false);
						UIManager.login.setVisible(false);
						UIManager.hallFrame.refresh();
						UIManager.hallFrame.setVisible(true);
						// new Thread(arg0, arg1)
					}
				});
				break;
			case Protocal.LOGIN_MAX_USER_REACHED:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						UIManager.login.label.setText("too many people");
						UIManager.login.button.setEnabled(true);
					}
				});
				break;
			case Protocal.LOGIN_USER_EXISTING:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						UIManager.login.label.setText("already loggined");
						UIManager.login.button.setEnabled(true);
					}
				});
			case Protocal.LOGIN_EXCEPTION:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						UIManager.login.label.setText("login exception");
						UIManager.login.button.setEnabled(true);
					}
				});
				break;
			default:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						UIManager.login.label.setText("unknow command");
						UIManager.login.button.setEnabled(true);
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
					UIManager.login.label.setText(e.getMessage());
					UIManager.login.button.setEnabled(true);
				}
			});
			// TODO: handle exception
		}
		
	}

}
