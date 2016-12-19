package com.myproject.service;

import com.myproject.entity.User;

public interface UserService {
	
	int vertifyUser(String username,String password);
	
	Integer insertUser(User user);
	
	Integer updateUser(User user);
	
	Integer delectUser(int userid);
}
