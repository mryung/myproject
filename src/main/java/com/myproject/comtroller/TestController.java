package com.myproject.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myproject.annotation.MethodLog;
import com.myproject.service.TestService;
import com.myproject.system.WebConfig;

@Controller
public class TestController extends BasicController{
	
	@Autowired
	private TestService test;
	
	@MethodLog(operType="测试",serviceName="首页")
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(){
		test.test();
		System.err.println(WebConfig.imgPath);
		return "test";
	}
}
