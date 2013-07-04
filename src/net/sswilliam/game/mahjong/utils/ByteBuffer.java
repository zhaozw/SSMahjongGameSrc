package net.sswilliam.game.mahjong.utils;

import java.util.ArrayList;

import net.sswilliam.java.utils.StringByteUtils;

public class ByteBuffer {

	private ArrayList<Byte> ret;
	public ByteBuffer(){
		ret = new ArrayList<Byte>();
	}
	public void append(byte b){
		ret.add(b);
	}
	public void append(String s){
		try {
			byte[] bytes = StringByteUtils.str2byte(s);
			append(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void append(byte[] bytes){
		if(bytes != null){
			int length = bytes.length;
			for(int i = 0;i<length;i++){
				ret.add(bytes[i]);
			}
		}
	}
	public byte[] toBytes(){
		int size = ret.size();
		byte[] bytes = new byte[size];
		for(int i = 0;i<size;i++){
			bytes[i] = ret.get(i).byteValue();
		}
		return bytes;
		
	}
	public void clear(){
		ret.clear();
	}
	
}
