package net.sswilliam.game.mahjong.controller.commands;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.controller.Controller;
import net.sswilliam.game.mahjong.controller.UserSession;
import net.sswilliam.game.mahjong.model.Hall;
import net.sswilliam.game.mahjong.model.Model;
import net.sswilliam.game.mahjong.utils.ByteBuffer;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.StringByteUtils;

import org.xsocket.connection.INonBlockingConnection;

public class LoadHallInformationCommand extends AbstractCommand {

	public LoadHallInformationCommand(byte[] data, INonBlockingConnection connection) throws InvalidaFormatCommandException{
		super(data, connection);
		if(data.length != 1){
			throw new InvalidaFormatCommandException();
		}
	}
	@Override
	public byte getCommandID() {
		// TODO Auto-generated method stub
		return Protocal.LOAD_HALL_INFORMATION;
	}

	@Override
	public void execute() {
		synchronized (Controller.MOCK_LOCK) {
			try {
				if(!Controller.getInstance().hasConnection(connection)){
					byte[] ret = new byte[2];
					ret[0] = Protocal.LOAD_HALL_INFORMATION;
					ret[1] = Protocal.COMMON_NOT_LOGIN;
					Controller.getInstance().writeBytes(connection, ret);;
					return;
				}
				
				String currentHallStatus = Model.getInstance().getHall().getCurrentInfo();
				byte[] currentHallStatusBytes = StringByteUtils.str2byte(currentHallStatus);
				long startTime = System.currentTimeMillis();
				ByteBuffer buffer = new ByteBuffer();
				buffer.append(Protocal.LOAD_HALL_INFORMATION);
				buffer.append(Protocal.LOAD_HALL_INFORMATION_SUCCESS);
				buffer.append(currentHallStatusBytes);
				Controller.getInstance().writeBytes(connection, buffer.toBytes());
				Log.Debug("Execute Time "+(System.currentTimeMillis() -startTime), this);
			} catch (Exception e) {
				byte[] ret = new byte[2];
				ret[0] = Protocal.LOAD_HALL_INFORMATION;
				ret[1] = Protocal.LOAD_HALL_INFORMATION_FAILED;
				Log.Excption(e);
				return;
			}
			
		}
		
	}

}
