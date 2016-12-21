package com.myproject.service.imp;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.UserDao;
import com.myproject.entity.User;
import com.myproject.service.UserService;
import com.myproject.util.CusAccessObjectUtil;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDao userdao;
	
	@Transactional
	@Override
	public Integer vertifyUser(String username, String password,HttpServletRequest request) {
		
		User user;
		user = userdao.findUserByUsername(username);
		if(user == null){
			return -2;
		}
		
		user = userdao.findUserByUserPassword(username, password);
		if(user == null){
			return -1;
		}
		
		//修改 用户 登录信息
		user.setUserLoginCount(user.getUserLoginCount() + 1);
		user.setUserLastLoginTime(user.getUserLoginTime());
		user.setUserLoginTime(new Date());
		user.setUserLastLoginIp(user.getUserLoginIp());
		user.setUserLoginIp(CusAccessObjectUtil.getIpAddress(request));
		userdao.updateUser(user);
		
		return user.getUserId();
	}

	@Override
	@Transactional
	public Integer insertUser(User user,HttpServletRequest request) {
		//验证用户信息
		User fromdatabase;
		fromdatabase = userdao.findUserByUsername(user.getUsername());
		if(fromdatabase != null){
			return -1; //用于已存在
		}
		//邮箱
		fromdatabase = userdao.findUserByEmail(user.getEmail());
		if(fromdatabase != null){
			return -2; //邮箱已存在
		}
		user.setUserGenTime(new Date());
		user.setUserLoginCount(1);
		user.setUserLastLoginTime(new Date());
		user.setUserLoginTime(new Date());
		user.setUserLastLoginIp(CusAccessObjectUtil.getIpAddress(request));
		user.setUserLoginIp(CusAccessObjectUtil.getIpAddress(request));
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
