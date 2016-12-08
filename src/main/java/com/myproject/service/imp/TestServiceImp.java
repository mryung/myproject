package com.myproject.service.imp;

import org.springframework.stereotype.Service;

import com.myproject.annotation.MethodLog;
import com.myproject.service.TestService;

@Service
public class TestServiceImp implements TestService {

	@Override
	@MethodLog(serviceName="testService",operType="hello")
	public void test() {
		System.out.println("**yulang**");
	}

}
