package net.sswilliam.game.mahjong.client.controller;

import net.sswilliam.game.mahjong.client.ClientContext;
import net.sswilliam.game.mahjong.client.IResponseHandler;

public abstract class ClientResponseHandlerBase implements IResponseHandler {

	protected ClientContext context;
	public ClientResponseHandlerBase(ClientContext context){
		this.context = context;
	}
	

}
