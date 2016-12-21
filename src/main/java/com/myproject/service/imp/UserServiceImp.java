package com.myproject.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dao.UserDao;
import com.myproject.entity.User;
import com.myproject.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDao userdao;

	@Override
	public Integer vertifyUser(String username, String password) {
		
		User user;
		user = userdao.findUserByUsername(username);
		if(user == null){
			return -2;
		}
		
		user = userdao.findUserByUserPassword(username, password);
		if(user == null){
			return -1;
		}
		return user.getUserId();
	}

	@Override
	public Integer insertUser(User user) {
		return userdao.insertUser(user);
	}

	@Override
	public Integer updateUser(User user) {
		return userdao.updateUser(user);
	}

	@Override
	public Integer delectUser(Integer userid) {
		User user = userdao.findUserbyId(userid);
		if(user == null){
			return -1;
		}
		return userdao.delectUser(user);
	}

}
