package com.myproject.comtroller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myproject.annotation.MethodLog;
import com.myproject.entity.User;
import com.myproject.service.UserService;

@Controller
public class LoginController extends BasicController {
	private static String PRE_PROJECT = "myproject_";
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	@MethodLog(serviceName="登录控制器",operType="index")
	public String login(Map<String,Object> map){
		
		return html("login", map);
	}
	
	@ResponseBody
	@RequestMapping(value="login",method=RequestMethod.POST)
	@MethodLog(serviceName="登录控制器",operType="vertify")
	public Map checklogin(HttpServletRequest request,String username,String password){
		System.out.println("hello");
		int userid = userService.vertifyUser(username, password,request);
		if(userid >= 0){
			HttpSession session = request.getSession();
			session.setAttribute(PRE_PROJECT+"userid", userid);
			session.setAttribute(PRE_PROJECT+"username", username);
			return successAjax("登录成功","test");
		}else{
			return errorAjax("用户名或密码错误");
		}
	}

	@RequestMapping(value="register",method=RequestMethod.GET)
	@MethodLog(serviceName="注册控制器",operType="registerhtml")
	public String registerHtml(Map<String,Object> map){
		return html("register", map);
	}
	
	@ResponseBody
	@RequestMapping(value="register",method=RequestMethod.POST)
	@MethodLog(serviceName="注册控制器",operType="register")
	public Map register(HttpServletRequest request,User user,String password1){
		System.out.println(user);
		if(!password1.equals(user.getPassword())){
			return errorAjax("输入的密码不一致");
		}
		int userid = userService.insertUser(user,request);
		if(userid >= 0){
			HttpSession session = request.getSession();
			session.setAttribute(PRE_PROJECT+"userid", userid);
			session.setAttribute(PRE_PROJECT+"username", user.getUsername());
			return successAjax("注册成功","test");
		}else{
			return errorAjax("用户名或者邮箱已经存在了");
		}
	}
		
}
