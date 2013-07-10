package net.sswilliam.game.mahjong.client.controller;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.StringByteUtils;

public class NotifySitTableResponseHandler extends ClientResponseHandlerBase {

	public NotifySitTableResponseHandler(ClientContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(final byte[] response) {
		// TODO Auto-generated method stub

		if(response.length < 4){
			handlerError("Not enough data");
			return;
		}
		final byte tableid = response[1];
		final byte seat = response[2];
		Log.Debug("handle the notify sit table response", this);
		byte[] contentBytes = Arrays.copyOfRange(response, 3, response.length);
		final String content;
		try {

			content = StringByteUtils.byte2str(contentBytes);
		} catch (Exception e) {
			handlerError(e.getMessage());
			return;
			// TODO: handle exception
		}
		Log.Debug("result: "+content, this);
		Log.Debug("updata hall: table:"+tableid+", seat:"+seat+", username:"+content, this);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(context.tableFrame.isVisible()){
					context.tableFrame.status.setText("update status of the table information successfully");
					context.tableFrame.sit(seat,content);
					return;
				}
				if(context.hallFrame.isVisible()){
					context.hallFrame.status.setText("update status of the hall information successfully");
					context.hallFrame.sit(tableid, seat, content);
					return;
				}
			}
		});
		
		
	}
	
	private void handlerError(final String msg){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(context.tableFrame.isVisible()){
					context.tableFrame.status.setText("[Notify Sit]:"+msg);
					return;
				}
				if(context.hallFrame.isVisible()){
					context.hallFrame.status.setText("[Notify Sit]:"+msg);
					return;
				}
			}
		});
	}

}
