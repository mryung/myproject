package com.myproject.message;

import java.util.HashMap;
import java.util.Map;

public class Message {
	
	//返回码
	public int code;
	//重定向的URL
	public String redire;
	//返回的消息
	public String msg;
	//返回的 额外消息
	public Map<String, Object> map;
	
	public Message newMessage(){
		return new Message();
	}
	public Message newMessage(int code,String msg){
		return new Message(code,msg);
	}
	public Message newMessage(int code,String msg,String redire){
		return new Message(code,msg,redire);
	}
	
	
	private Message() {
	}
	private Message(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	private Message(int code,String msg,String redire) {
		this(code,msg);
		this.redire = redire;
	}
	public void putParam(String key,Object value){
		if(map == null){
			map = new HashMap<String,Object>();
		}
		map.put(key, value);
	}
}
