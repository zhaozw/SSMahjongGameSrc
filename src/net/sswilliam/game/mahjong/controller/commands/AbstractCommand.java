package net.sswilliam.game.mahjong.controller.commands;

import org.xsocket.connection.INonBlockingConnection;

public abstract class AbstractCommand implements ICommand{

	protected byte[] data;

	protected INonBlockingConnection connection;
	public AbstractCommand(byte[] bytes, INonBlockingConnection connection){
		this.data = bytes;
		this.connection = connection;
	}
}
