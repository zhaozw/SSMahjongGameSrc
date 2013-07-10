package net.sswilliam.game.mahjong.client.controller;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.StringByteUtils;

public class LoadHallInformationResponseHandler extends
		ClientResponseHandlerBase {

	public LoadHallInformationResponseHandler(ClientContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(final byte[] response) {
		// TODO Auto-generated method stub
		switch (response[1]) {
		case Protocal.LOAD_HALL_INFORMATION_FAILED:
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					context.hallFrame.status.setText("read data failed");

				}
			});

			break;
		case Protocal.COMMON_NOT_LOGIN:
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					context.hallFrame.status.setText("need login first");

				}
			});
			break;
		case Protocal.LOAD_HALL_INFORMATION_SUCCESS:

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					context.hallFrame.status.setText("load Data success");
					byte[] infoData = Arrays.copyOfRange(response, 2, response.length);
					try {

						String retStr = StringByteUtils.byte2str(infoData);
						String[] eachTable = retStr.split(":");
						System.out.println(eachTable.length);
						for (int i = 0; i < eachTable.length; i++) {
							context.hallFrame.updateTables(i, eachTable[i]);
						}
						Log.Debug(retStr, this);
					} catch (Exception e) {

						context.hallFrame.status.setText("Error "
								+ e.getMessage());
						// TODO: handle exception
					}
				}
			});
			break;
		default:
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					context.hallFrame.status
							.setText("not retrieve hall information response");

				}
			});
			break;
		}
	}

}
