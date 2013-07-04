package net.sswilliam.game.mahjong.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.xsocket.connection.INonBlockingConnection;
import org.xsocket.connection.NonBlockingConnection;

import net.sswilliam.game.mahjong.controller.UserSession;
import net.sswilliam.game.platform.user.User;

public class Hall {

	public static Object hallLock = new Object();
	private ArrayList<Table> tables;
	private boolean initialized = false;
	private int tablesCapacity;
	
	public Hall(){
		
	}
	public void init(int maxUser, int tableNum){
		if(initialized){
			return;
		}
		this.tablesCapacity = tableNum;
		this.tables = new ArrayList<Table>(tableNum);
		for(int i = 0;i<tableNum;i++){
			Table table = new Table(i);
			this.tables.add(table);
		}
		initialized = true;
	}
	public void addUser(){
		
	}
	public void removeUser(){
		
	}
	
	public Table getTableByIndex(int id) throws TableNotFoundException{
		if(id<0 || id > tablesCapacity-1){
			throw new TableNotFoundException(id);
		}
		return tables.get(id);
		
	}
	
	public Table retrieveTable(int tableid){
		return tables.get(tableid);
	}
	
	public String getCurrentInfo(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<tablesCapacity;i++){
			Table table = tables.get(i);
			sb.append(table.getStatus());
			sb.append(",");
			for(byte j = 0;j<4;j++){
				sb.append(table.getUserName(j));
				sb.append(',');
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append(":");
		}
		return sb.deleteCharAt(sb.length()-1).toString();
	}
	
}
