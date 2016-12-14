package com.myproject.comtroller;

import java.util.Map;

import com.myproject.message.Message;
import com.myproject.system.WebConfig;

public class BasicController {
	
	
	public Message successAjax(String msg){
		return Message.newMessage(1, msg);
	}
	
	public Message successAjax(String url,String msg){
		return Message.newMessage(1, msg, url);
	}
	
	public Message errorAjax(String msg){
		return Message.newMessage(0, msg);
	}
	
	public Message json(int code, String msg,Message message){
		message.setCode(code);
		message.setMsg(msg);

		return message;
	}
	
	public void putInfo(String key,Object value,Message message){
		message.putParam(key, value);
	}
	
	//返回视图
	public String html(String html,Map<String, Object> map){
		
		map.put(WebConfig.host, WebConfig.hostName);
		map.put(WebConfig.server, WebConfig.serverPath);
		map.put(WebConfig.js, WebConfig.jsPath);
		map.put(WebConfig.css, WebConfig.cssPath);
		map.put(WebConfig.img, WebConfig.imgPath);
		return html;
	}
	
}
