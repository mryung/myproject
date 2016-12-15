package com.myproject.comtroller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myproject.annotation.MethodLog;

@Controller
public class LoginController extends BasicController {
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	@MethodLog(serviceName="登录控制器",operType="index")
	public String login(Map<String,Object> map){
		
		return html("login", map);
	}
	
	@ResponseBody
	@RequestMapping(value="login",method=RequestMethod.POST)
	@MethodLog(serviceName="登录控制器",operType="vertify")
	public Map checklogin(){
		System.out.println("hello");
		return successAjax("登录成功","test");
	}
		
}
