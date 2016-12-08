package com.myproject.comtroller;

import java.util.HashMap;

import org.springframework.web.servlet.ModelAndView;

import com.myproject.message.Message;
import com.myproject.system.WebConfig;

public class BasicController {
	
	private Message message;
	
	public Message success(String msg){
		message.msg = msg;
		return message;
	}
	
	public Message success(String url,String msg){
		message.redire = url;
		message.msg = msg;
		return message;
	}
	
	public void putInfo(String key,Object value){
		if(null == message.map){
			message.map = new HashMap<String,Object>();
		}
		message.map.put(key, value);
	}
	
	//返回视图
	public ModelAndView html(String html){
		ModelAndView modelAndView = new ModelAndView(html);
		
		modelAndView.addObject(WebConfig.host, WebConfig.hostName);
		modelAndView.addObject(WebConfig.server, WebConfig.serverPath);
		modelAndView.addObject(WebConfig.js, WebConfig.jsPath);
		modelAndView.addObject(WebConfig.css, WebConfig.cssPath);
		modelAndView.addObject(WebConfig.img, WebConfig.imgPath);
		return modelAndView;
	}
	
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	
	
}
