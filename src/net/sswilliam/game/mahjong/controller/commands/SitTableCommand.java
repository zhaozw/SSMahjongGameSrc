package net.sswilliam.game.mahjong.controller.commands;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.controller.Controller;
import net.sswilliam.game.mahjong.controller.UserSession;
import net.sswilliam.game.mahjong.controller.UserSession.UserState;
import net.sswilliam.game.mahjong.model.Hall;
import net.sswilliam.game.mahjong.model.Model;
import net.sswilliam.game.mahjong.model.Table;
import net.sswilliam.game.mahjong.model.TableNotFoundException;
import net.sswilliam.game.mahjong.utils.ByteBuffer;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.StringByteUtils;

import org.xsocket.connection.INonBlockingConnection;

public class SitTableCommand extends AbstractCommand {

	public SitTableCommand(byte[] bytes, INonBlockingConnection connection) throws InvalidaFormatCommandException {
		super(bytes, connection);
		if(data.length !=3)
			throw new InvalidaFormatCommandException();
	}

	@Override
	public byte getCommandID() {
		// TODO Auto-generated method stub
		return Protocal.SIT_TABLE;
	}

	@Override
	public void execute() {
		synchronized (Controller.MOCK_LOCK) {
			try {
				if (!Controller.getInstance().hasConnection(connection)) {
					byte[] ret = new byte[2];
					ret[0] = Protocal.SIT_TABLE;
					ret[1] = Protocal.COMMON_NOT_LOGIN;
					Log.Debug("Not login", this);
					Controller.getInstance().writeBytes(connection, ret);
					return;
				}

				UserSession session = Controller.getInstance().getSessionByConnection(connection);
				if(session.getState() != UserState.FREE){
					byte[] ret = new byte[2];
					ret[0] = Protocal.SIT_TABLE;
					ret[1] = Protocal.SIT_TABLE_NOT_RIGHT_USER_STATE;
					Controller.getInstance().writeBytes(connection, ret);
					return;
				}
				
				Hall hall = Model.getInstance().getHall();
				Table table = hall.getTableByIndex(data[1]);
				Log.Debug("sessin id"+session.getSessionID() ,this);
				Log.Debug("session state"+session.getState(), this);
				
				byte seat = data[2];
				if(seat < 0 || seat > 3){
					byte[] ret = new byte[2];
					ret[0] = Protocal.SIT_TABLE;
					ret[1] = Protocal.SIT_TABLE_SEAT_NOT_EXIST;
					Controller.getInstance().writeBytes(connection, ret);
					return;
				}
				if(!table.isSeatEmpty(seat)){
					byte[] ret = new byte[2];
					ret[0] = Protocal.SIT_TABLE;
					ret[1] = Protocal.SIT_TABLE_SIT_ALREADY_TAKEN;
					Controller.getInstance().writeBytes(connection, ret);
					return;
				}
				table.setSession(seat, session);
				session.sit();
				byte[] ret = new byte[2];
				ret[0] = Protocal.SIT_TABLE;
				ret[1] = Protocal.SIT_TABLE_SUCCESS;
				Controller.getInstance().writeBytes(connection, ret);
				
				ByteBuffer bf = new ByteBuffer();
				bf.append(Protocal.NOTIFY_SIT_TABLE);
				bf.append(data[1]);
				bf.append(data[2]);
				bf.append(session.getUser().username);
				byte[] retForBroadCast = bf.toBytes();
				for(byte i = 0;i<4;i++){
					UserSession otherSession = table.getSession(i);
					if(i != seat && otherSession != null){
						INonBlockingConnection otherConnection = otherSession.getConnection();
						if(otherConnection != null){
							Log.Debug("send to connection"+otherSession.getSessionID(), this);
							Controller.getInstance().writeBytes(otherConnection, retForBroadCast);
						}
					}	
				}
				Controller.getInstance().broadCastToHall(retForBroadCast);
				
			} catch(TableNotFoundException e){

				byte[] ret = new byte[2];
				ret[0] = Protocal.SIT_TABLE;
				ret[1] = Protocal.SIT_TABLE_TABLE_NOT_EXIST;
				Controller.getInstance().writeBytes(connection, ret);
			}
			catch (Exception e) {
				// TODO: handle exception
				byte[] ret = new byte[2];
				ret[0] = Protocal.SIT_TABLE;
				ret[1] = Protocal.SIT_TABLE_EXCEPTION_FAILED;
				Controller.getInstance().writeBytes(connection, ret);
			}
		}
	
	}

}
