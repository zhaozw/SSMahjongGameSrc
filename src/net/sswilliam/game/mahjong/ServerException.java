package net.sswilliam.game.mahjong;

import net.sswilliam.java.logger.Log;

public class ServerException extends RuntimeException {

	public ServerException(String message){
		super(message);
		Log.Error(message, ServerException.class);
	}
}
