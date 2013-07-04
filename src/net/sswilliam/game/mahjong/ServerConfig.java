package net.sswilliam.game.mahjong;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import net.sswilliam.java.logger.Log;

public class ServerConfig {

	private static final String SERVER_PORT_KEY = "server_port";
	private static final String MAX_USER_KEY = "max_user";
	private static final String TABLE_NUM_KEY = "table_num";
	private static final String RULE_KEY = "rule";
	private static final String USER_DB_KEY = "user_db";
	public static final int PORT;
	public static final int MAX_USER;
	public static final int TABLE_NUM;
	public static final String RULE;
	public static final String USER_DB;
//	public
	static{
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("server.properties")));
			String serverPort = properties.getProperty(SERVER_PORT_KEY);
			PORT = Integer.parseInt(serverPort);
			Log.Info(SERVER_PORT_KEY + " : " + PORT, ServerConfig.class);
			
			String maxUser = properties.getProperty(MAX_USER_KEY);
			MAX_USER = Integer.parseInt(maxUser);
			Log.Info(MAX_USER_KEY+" : "+MAX_USER, ServerConfig.class);
			
			String tableNum = properties.getProperty(TABLE_NUM_KEY);
			TABLE_NUM = Integer.parseInt(tableNum);
			Log.Info(TABLE_NUM_KEY+" : "+TABLE_NUM, ServerConfig.class);
			
			RULE = properties.getProperty(RULE_KEY);
			Log.Info(RULE_KEY+" : "+RULE, ServerConfig.class);
			
			USER_DB = properties.getProperty(USER_DB_KEY);
			Log.Info(USER_DB_KEY+" : "+RULE, ServerConfig.class);
		} catch (Exception e) {
			// TODO: handle exception
			Log.Excption(e);
			throw new ServerException("Error during when load the configration");
		}
	}
	public static void init(){}
}
