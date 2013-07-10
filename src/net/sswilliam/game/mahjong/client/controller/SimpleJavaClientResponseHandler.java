package net.sswilliam.game.mahjong.client.controller;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.game.mahjong.client.IResponseHandler;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.StringByteUtils;

public class SimpleJavaClientResponseHandler implements IResponseHandler {

	
	private ClientContext context;
	public SimpleJavaClientResponseHandler(ClientContext context){
		this.context = context;
	}
	@Override
	public void handle(byte[] response) {
		// TODO Auto-generated method stub

		synchronized (context.slock) {
			if (response != null && response.length > 1) {
				switch (response[0]) {
				case Protocal.LOGIN:
					new LoginResponseHandler(context).handle(response);
					break;

				case Protocal.LOAD_HALL_INFORMATION:
					new LoadHallInformationResponseHandler(context).handle(response);
					break;

				case Protocal.SIT_TABLE:
					new SitTableResponseHandler(context).handle(response);
					break;
				case Protocal.NOTIFY_SIT_TABLE:
					new NotifySitTableResponseHandler(context).handle(response);
					break;
				default:
					break;
				}
			}
		}
		

	}

	public void handleLoadHallInfomation(final byte[] data) {

		
	}

	public void handleLogin(final byte[] data) {
		

	}

	public void handleSitTable(final byte[] data) {
		

	}

	
	
	public void handleNotifySitTable(byte[] data){
		byte[] content = Arrays.copyOfRange(data, 1, data.length);
	}
}
