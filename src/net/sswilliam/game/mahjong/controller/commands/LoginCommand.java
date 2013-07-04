package net.sswilliam.game.mahjong.controller.commands;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.UUID;

import net.sswilliam.game.mahjong.Protocal;
import net.sswilliam.game.mahjong.controller.Controller;
import net.sswilliam.game.mahjong.controller.UserSession;
import net.sswilliam.game.mahjong.model.Hall;
import net.sswilliam.game.mahjong.model.Model;
import net.sswilliam.game.mahjong.utils.ByteBuffer;
import net.sswilliam.game.platform.user.User;
import net.sswilliam.game.platform.user.UserManager;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.StringByteUtils;

import org.xsocket.connection.INonBlockingConnection;

public class LoginCommand extends AbstractCommand {

	private String userid;
	private String loginname;
	private String password;
	
	public LoginCommand(byte[] bytes,INonBlockingConnection connection) throws UnsupportedEncodingException,InvalidaFormatCommandException{
		super(bytes,connection);
		byte[] loginInfoBytes = Arrays.copyOfRange(this.data, 1, this.data.length);
		String loginInfoStr = StringByteUtils.byte2str(loginInfoBytes);
		String[] datas = loginInfoStr.split(",");

		if(datas.length != 2)
			throw new InvalidaFormatCommandException();
		this.loginname = datas[0];
		this.password = datas[1];
	}
	
	@Override
	public byte getCommandID() {
		return Protocal.LOGIN;
	}

	@Override
	public void execute() {

		//retrieve the user data
		User user;
		try {
			user = UserManager.getInstance().validateUser(loginname, password);
		} catch (Exception e) {
			byte[] ret = new byte[2];
			ret[0] = Protocal.LOGIN;
			ret[1] = Protocal.LOGIN_EXCEPTION;
			Controller.getInstance().writeBytes(connection, ret);
			return;
		}
		
		
		synchronized (Controller.MOCK_LOCK) {
			if(!Controller.getInstance().hasCapacityForLogin()){
				byte[] retbytes = new byte[2];
				retbytes[0] = Protocal.LOGIN;
				retbytes[1] = Protocal.LOGIN_MAX_USER_REACHED;
				Controller.getInstance().writeBytes(connection, retbytes);
				Controller.getInstance().closeConnection(connection);
				return;
			}
			
			boolean hasConnection = Controller.getInstance().hasConnection(this.connection);
			if(hasConnection){
				byte[] retbytes = new byte[2];
				retbytes[0] = Protocal.LOGIN;
				retbytes[1] = Protocal.LOGIN_CONNECTION_EXITING;
				Controller.getInstance().writeBytes(connection, retbytes);
				return;
			}
			try {
				if(user == null){
					byte[] retbytes = new byte[2];
					retbytes[0] = Protocal.LOGIN;
					retbytes[1] = Protocal.LOGIN_FAILED;
					Controller.getInstance().writeBytes(connection, retbytes);
					return;
				}else{
					if(Controller.getInstance().hasUserID(user.uid)){
						byte[] retbytes = new byte[2];
						retbytes[0] = Protocal.LOGIN;
						retbytes[1] = Protocal.LOGIN_USER_EXISTING;
						Controller.getInstance().writeBytes(connection, retbytes);
						return;
					}else{
						//if login success
						//add an user session
						//return the successful signal
						UserSession session = new UserSession(UUID.randomUUID().toString(), connection, user);
						Controller.getInstance().addSession(session);
						ByteBuffer byteBuffer = new ByteBuffer();
						byteBuffer.append(Protocal.LOGIN);
						byteBuffer.append(Protocal.LOGIN_SUCCESS);
						byteBuffer.append(user.uid);
						byteBuffer.append(",");
						byteBuffer.append(user.username);
						byteBuffer.append(",");
						byteBuffer.append(user.head);
						
						Controller.getInstance().writeBytes(connection, byteBuffer.toBytes());
						
						return;
					}
				}
			} catch (Exception e) {
				Log.Excption(e);
				byte[] retBytes = new byte[2];
				retBytes[0] = Protocal.LOGIN;
				retBytes[1] = Protocal.LOGIN_EXCEPTION;
				Controller.getInstance().writeBytes(connection, retBytes);
				return;
			}
		}
	}

	
}
