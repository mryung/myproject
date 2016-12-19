package com.myproject.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	private String email;

	@Column(name="login_name")
	private String loginName;

	@Column(name="organization_id")
	private int organizationId;

	private String password;

	private String phone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="user_gen_time")
	private Date userGenTime;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="user_last_login_time")
	private Date userLastLoginTime;

	@Column(name="user_login_count")
	private int userLoginCount;

	@Column(name="user_login_ip")
	private String userLoginIp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="user_login_time")
	private Date userLoginTime;

	private String username;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getUserGenTime() {
		return this.userGenTime;
	}

	public void setUserGenTime(Date userGenTime) {
		this.userGenTime = userGenTime;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getUserLastLoginTime() {
		return this.userLastLoginTime;
	}

	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}

	public int getUserLoginCount() {
		return this.userLoginCount;
	}

	public void setUserLoginCount(int userLoginCount) {
		this.userLoginCount = userLoginCount;
	}

	public String getUserLoginIp() {
		return this.userLoginIp;
	}

	public void setUserLoginIp(String userLoginIp) {
		this.userLoginIp = userLoginIp;
	}

	public Date getUserLoginTime() {
		return this.userLoginTime;
	}

	public void setUserLoginTime(Date userLoginTime) {
		this.userLoginTime = userLoginTime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}