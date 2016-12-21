package com.myproject.service;

import javax.servlet.http.HttpServletRequest;

import com.myproject.entity.User;

public interface UserService {
	
	Integer vertifyUser(String username,String password,HttpServletRequest request);
	
	Integer insertUser(User user,HttpServletRequest request);
	
	Integer updateUser(User user);
	
	Integer delectUser(Integer userid);
}
