package net.sswilliam.game.mahjong.client.controller;

import javax.swing.SwingUtilities;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.client.ClientContext;

public class SitTableResponseHandler extends ClientResponseHandlerBase {

	public SitTableResponseHandler(ClientContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(final  byte[] response) {
		// TODO Auto-generated method stub
		try {
			switch (response[1]) {
			case Protocal.SIT_TABLE_EXCEPTION_FAILED:
				handleErrorForSit("sit exception");
				break;

			case Protocal.SIT_TABLE_NOT_RIGHT_USER_STATE:
				handleErrorForSit("you are no in hall:");
				break;

			case Protocal.SIT_TABLE_SEAT_NOT_EXIST:
				handleErrorForSit("The seat is not there");

				break;
			case Protocal.SIT_TABLE_SIT_ALREADY_TAKEN:
				handleErrorForSit("The seat is not already taken by someone");
				break;

			case Protocal.SIT_TABLE_TABLE_NOT_EXIST:
				handleErrorForSit("Table is not there");
				break;
			case Protocal.SIT_TABLE_SUCCESS:
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						context.hallFrame.tablesScroll.setVisible(true);
						context.hallFrame.status.setText("[SIT TABLE] sit success");
						context.hallFrame.setVisible(false);
						context.tableFrame.setVisible(true);
					}
				});
				break;
			default:
				handleErrorForSit("not such responst");
				break;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			handleErrorForSit(e.getMessage());
			// TODO: handle exception
		}
	}
	private void handleErrorForSit(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				context.hallFrame.tablesScroll.setVisible(true);
				context.hallFrame.status.setText("[SIT TABLE]"+msg);
			}
		});
	}
	

}
