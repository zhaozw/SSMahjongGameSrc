package net.sswilliam.game.mahjong.controller.commands;

public interface ICommand {

	public byte getCommandID();
	public void execute();
}
