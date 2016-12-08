package com.myproject.message;

import java.util.Map;

public class Message {
	
	//返回的状态码  1:表示正确   0:表示警告   -1：表示错误 
	public int code;
	//重定向的url
	public String redire;
	//出错消息： 用做 弹出框
	public String msg;
	//列表信息
	public Map<String, Object> map;
	
	
	
}
