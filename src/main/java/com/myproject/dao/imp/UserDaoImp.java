package com.myproject.dao.imp;

import org.springframework.stereotype.Repository;

import com.myproject.dao.UserDao;
import com.myproject.entity.User;

@Repository
public class UserDaoImp extends BaseDaoImpl<User, Integer> implements UserDao {

	@Override
	public User findUserbyId(Integer userid) {
		return find(userid);
	}

	@Override
	public User findUserByUsername(String username) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_user where username = '"+username+"'");
		return super.findBySQL(sql.toString(), User.class);
	}

	@Override
	public User findUserByUserPassword(String username, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_user where username = '"+username+"' and password = '"+password+"'");
		return super.findBySQL(sql.toString(), User.class);
	}

	@Override
	public Integer insertUser(User user) {
		super.persist(user);
		return user.getUserId();
	}

	@Override
	public Integer updateUser(User user) {
		super.merge(user);
		return user.getUserId();
	}

	@Override
	public Integer delectUser(User user) {
		super.remove(user.getUserId());
		return user.getUserId();
	}

	@Override
	public User findUserByEmail(String email) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_user where email = '"+email+"'");
		return super.findBySQL(sql.toString(), User.class);
	}

}
