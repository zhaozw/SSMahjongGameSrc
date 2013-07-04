package net.sswilliam.game.mahjong;

import net.sswilliam.game.mahjong.controller.Controller;
import net.sswilliam.game.mahjong.model.Hall;
import net.sswilliam.game.mahjong.model.Model;
import net.sswilliam.game.platform.user.UserManager;

public class ServerMain {

	private static ServerMain serverInstance = new ServerMain();
	
	private boolean started = false;
	private ServerMain(){
		
	}
	public static ServerMain getInstance(){
		return serverInstance;
	}
	public void start(){
		if(!started){
			started = true;
			ServerConfig.init();
			
			Model.getInstance().init(ServerConfig.MAX_USER, ServerConfig.TABLE_NUM);
			UserManager.getInstance().init(ServerConfig.USER_DB);
			Controller.getInstance().init(ServerConfig.PORT,ServerConfig.MAX_USER);
		}
		
	}
	public void stop(){
		if(started){
			Controller.getInstance().finalize();
			Model.getInstance().finalize();
			UserManager.getInstance().finalize();
			started = false;
			
		}
	}
	public static void main(String[] args) {
		ServerMain.getInstance().start();
	}
}
