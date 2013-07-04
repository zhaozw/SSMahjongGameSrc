package net.sswilliam.game.mahjong.controller.commands;

import org.xsocket.connection.INonBlockingConnection;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.java.logger.Log;

public class CommandFactory {

	public static ICommand getCommand(INonBlockingConnection connection) {

		try {
			int available = connection.available();
			if(available == -1){
				return null;
			}
			byte[] receivedData = connection.readBytesByLength(available);
			if (receivedData.length == 0) {
				Log.Error("empty bytes received", CommandFactory.class);
				return null;
			}
			switch (receivedData[0]) {

			case Protocal.LOGIN:
				return new LoginCommand(receivedData, connection);

			case Protocal.LOAD_HALL_INFORMATION:
				return new LoadHallInformationCommand(receivedData, connection);
				
			case Protocal.SIT_TABLE:
				Log.Debug("create sit table commane", CommandFactory.class);
				return new SitTableCommand(receivedData, connection);
			default:
				Log.Error("unrecognized command id: " + receivedData[0],
						CommandFactory.class);
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.Excption(e);
			return null;
		}

	}
}
