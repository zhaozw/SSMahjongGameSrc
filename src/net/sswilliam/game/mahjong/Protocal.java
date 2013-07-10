package net.sswilliam.game.mahjong;

public class Protocal {

	
	public static final byte COMMON_NOT_LOGIN = -1;
	public static final byte COMMON_INVALIDE_COMMAND_SYNTAX = -2;
	
	public static final byte LOGIN = 0;
	public static final byte LOGIN_CONNECTION_EXITING = 0;
	public static final byte LOGIN_USER_EXISTING = 1;
	public static final byte LOGIN_MAX_USER_REACHED = 2;
	public static final byte LOGIN_SUCCESS = 3;
	public static final byte LOGIN_FAILED = 4;
	public static final byte LOGIN_EXCEPTION = 5;
	
	public static final byte LOAD_HALL_INFORMATION = 1;
	public static final byte LOAD_HALL_INFORMATION_SUCCESS = 0;
	public static final byte LOAD_HALL_INFORMATION_FAILED = 1;
	
	public static final byte SIT_TABLE = 2;
	public static final byte SIT_TABLE_SIT_ALREADY_TAKEN= 0;
	public static final byte SIT_TABLE_TABLE_NOT_EXIST = 1;
	public static final byte SIT_TABLE_SEAT_NOT_EXIST = 2;
	public static final byte SIT_TABLE_EXCEPTION_FAILED = 3;
	public static final byte SIT_TABLE_SUCCESS = 4;
	public static final byte SIT_TABLE_NOT_RIGHT_USER_STATE = 5;
	
	
	public static final byte NOTIFY_SIT_TABLE = 3;
	
	public static final byte LOAD_TABLE_INFORMATION = 4;
	
	
	
}
