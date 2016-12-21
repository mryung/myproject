package com.myproject.message;

import java.util.HashMap;
import java.util.Map;

public class Message extends HashMap<String, Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -709551315923967579L;
	//返回码
//	public int code; 0 表示错误 1表示返回正确
	//重定向的URL
//	public String redire;
	//返回的消息
//	public String msg;
	//返回的 额外消息
//	public Map<String, Object> map;
	
	public static Message newMessage(){
		return new Message();
	}
	public static Message newMessage(int code,String msg){
		return new Message(code,msg);
	}
	public static Message newMessage(int code,String msg,String redire){
		return new Message(code,msg,redire);
	}
	
	
	private Message() {
	}
	private Message(int code,String msg) {
		put("status", code);
		put("info", msg);
	}
	private Message(int code,String msg,String redire) {
		this(code,msg);
		put("url", redire);
	}
	
	public void setCode(int code){
		put("status", code);
	}
	public void setRedire(String redire){
		put("url", redire);
	}
	public void setMsg(String msg){
		put("info",msg);
	}
	
	public void putParam(String key,Object value){
		put(key, value);
	}
}
