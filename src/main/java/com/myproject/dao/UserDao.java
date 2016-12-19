package com.myproject.dao;

import com.myproject.entity.User;

public interface UserDao {
	
	User findUserbyId(Integer userid);
	User findUserByUsername(String username);
	User findUserByUserPassword(String username,String password);
	
	Integer insertUser(User user);
	
	Integer updateUser(User user);
	
	Integer delectUser(User user);
}
