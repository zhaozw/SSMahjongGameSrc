package net.sswilliam.game.mahjong.controller;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.channels.ClosedChannelException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import net.sswilliam.game.mahjong.ServerException;
import net.sswilliam.game.mahjong.controller.UserSession.UserState;
import net.sswilliam.game.mahjong.controller.commands.CommandFactory;
import net.sswilliam.game.mahjong.controller.commands.ICommand;
import net.sswilliam.java.logger.Log;
import net.sswilliam.java.utils.StringByteUtils;

import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.INonBlockingConnection;
import org.xsocket.connection.IServer;
import org.xsocket.connection.NonBlockingConnection;
import org.xsocket.connection.Server;

public class Controller implements IDataHandler, IConnectHandler, IDisconnectHandler {

	public static final Object MOCK_LOCK = new Object();
	private static Controller instance = new Controller();
	
	private boolean initialized = false;
	private IServer server;
	

	private int userCapacity;
	
	private Controller(){
		
	}
	public static Controller getInstance(){
		return instance;
	}
	
	public void init(int port, int userCapacity){
		if(initialized)
			return;
		try {
			server = new Server(port, this);
			Log.Info("start server at port:"+port, Controller.class);
//			serverThread = new Thread(server, "xsocket_thread");
			this.userCapacity = userCapacity;
			sessions = new ArrayList<UserSession>(this.userCapacity);
			initialized = true;
			server.run();
		} catch (IOException e) {
			Log.Excption(e);
			throw new ServerException("Error when create the xSocket instance");
			// TODO: handle exception
		}
		
	}
	public void finalize(){
		if(initialized){
			try {
				server.close();
				this.userCapacity = 0;
				sessions = null;
				server = null;
				initialized = false;
			} catch (IOException e) {
				Log.Excption(e);
				throw new ServerException("Error when stop the xSocket instance");
			}
			
		}
	}
	

	
	@Override
	public boolean onDisconnect(INonBlockingConnection connection) throws IOException {
		// TODO Auto-generated method stub
		if(connection != null){

//			Log.Info("[Client on Connected]ip:"+connection.getRemoteAddress().getHostAddress()+",port:"+connection.getRemotePort(), this);
		}
		return true;
	}
	@Override
	public boolean onConnect(INonBlockingConnection connection) throws IOException,
			BufferUnderflowException, MaxReadSizeExceededException {
		// TODO Auto-generated method stub
		Log.Info("[Client on Connected]ip:"+connection.getRemoteAddress().getHostAddress()+",port:"+connection.getRemotePort(), this);
		return true;
	}
	@Override
	public boolean onData(INonBlockingConnection connection) throws IOException,
			BufferUnderflowException, ClosedChannelException,
			MaxReadSizeExceededException {
//		Log.Info("data received "+connection.available(), this);
		ICommand command = CommandFactory.getCommand(connection);
		if(command != null){
			command.execute();
		}
		return true;
	}
	
	
	
	public void writeByte(INonBlockingConnection connection, byte value){
	
		try {
			Log.Debug("write int value:"+value, this);
			connection.write(value);
			connection.flush();
		} catch (IOException e) {
			Log.Excption(e);
			handleSendFail(connection);
		}
	}
	public void writeString(INonBlockingConnection connection, String value){
		try {

			Log.Debug("write string value:"+value, this);
			byte[] bytes = StringByteUtils.str2byte(value+",");
			connection.write(bytes, 0, bytes.length);
			connection.flush();
		} catch (IOException e) {
			Log.Excption(e);
			handleSendFail(connection);
		}
	}
	public void writeBytes(INonBlockingConnection connection,byte[] bytes){
		try {
			Log.Debug("write bytes", this);
			connection.write(bytes,0,bytes.length);
			connection.flush();
		} catch (Exception e) {
			Log.Excption(e);
			handleSendFail(connection);
		}
	}
	public void closeConnection(INonBlockingConnection connection){
		try {
			connection.close();
		} catch (Exception e) {
			Log.Excption(e);
			
		}
	}
	
	private void handleSendFail(INonBlockingConnection connection){
		try {
			UserSession userSession = getSessionByConnection(connection);
			if(userSession == null){
				Log.Info("[Handle the connection fail]:There is no connection", this);
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	public boolean hasUserID(String userid){
		try {
			UserSession session = getSessionByUserID(userid);
			if(session != null){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	public boolean hasConnection(INonBlockingConnection connection){
		try {
			UserSession userSession = getSessionByConnection(connection);
			Log.Debug("userSession"+userSession, this);
			if(userSession != null){
				return true;
			}
			return false;
		} catch (UserSessionNotFoundException e) {
			return false;
		}
	}
	
	public static final Object SESSION_LOCK = new Object();
	
	private ArrayList<UserSession> sessions;
	public void addSession(UserSession session){
		sessions.add(session);
	}
	private void removeSession(UserSession session) throws UserSessionNotFoundException{
		if(sessions.contains(session)){
			sessions.remove(session);
		}else{
			throw new UserSessionNotFoundException();
		}
		
	}
	public void removeSessionByUserid(String userid) throws UserSessionNotFoundException{
		UserSession session = getSessionByUserID(userid);
		sessions.remove(session);
	}
	public UserSession getSessionByConnection(INonBlockingConnection connection) throws UserSessionNotFoundException{
		int size = sessions.size();
		for(int i = 0;i<size;i++){
			UserSession session = sessions.get(i);
			if(session.getConnection() == connection){
				return session;
			}
		}
		throw new UserSessionNotFoundException();
	}
	public UserSession getSessionByUserID(String userid) throws UserSessionNotFoundException{
		int size = sessions.size();
		for(int i = 0;i<size;i++){
			UserSession session = sessions.get(i);
			if(session.getUserId().equals(userid)){
				return session;
			}
		}
		throw new UserSessionNotFoundException();
	}
	public boolean hasCapacityForLogin(){
		return sessions.size() < userCapacity;
	}
	
	public void broadCastToHall(byte[] bytes){
		Log.Debug("Start broad case in hall to "+sessions.size()+" user", this);
		for(int i = 0;i<sessions.size();i++){
			UserSession session = sessions.get(i);
			if(session != null && session.getConnection() != null && session.getState() == UserState.FREE){

				Log.Debug("Start broad case in hall to "+session.getSessionID()+" user: "+session.getUser().loginname, this);
				writeBytes(session.getConnection(), bytes);
			}
		}
	}
	
}
