package com.myproject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_user", catalog = "test")
public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private Integer userAge;
	private String phone;
	private String email;
	private Date userGenTime;
	private Date userLoginTime;
	private Date userLastLoginTime;
	private String userLoginIp;
	private String userLastLoginIp;
	private Integer userLoginCount = 10 ;
	private Integer class_;
	private String description = "小红果";
	private Integer deleted = 1;

	// Constructors

	public User() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "username", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "user_age")
	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	@Column(name = "phone", length = 100)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "user_gen_time", length = 19)
	public Date getUserGenTime() {
		return this.userGenTime;
	}

	public void setUserGenTime(Date userGenTime) {
		this.userGenTime = userGenTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "user_login_time", length = 19)
	public Date getUserLoginTime() {
		return this.userLoginTime;
	}

	public void setUserLoginTime(Date userLoginTime) {
		this.userLoginTime = userLoginTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "user_last_login_time", length = 19)
	public Date getUserLastLoginTime() {
		return this.userLastLoginTime;
	}

	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}

	@Column(name = "user_login_ip", length = 100)
	public String getUserLoginIp() {
		return this.userLoginIp;
	}

	public void setUserLoginIp(String userLoginIp) {
		this.userLoginIp = userLoginIp;
	}

	@Column(name = "user_login_count")
	public Integer getUserLoginCount() {
		return this.userLoginCount;
	}

	public void setUserLoginCount(Integer userLoginCount) {
		this.userLoginCount = userLoginCount;
	}

	@Column(name = "class")
	public Integer getClass_() {
		return this.class_;
	}

	public void setClass_(Integer class_) {
		this.class_ = class_;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "deleted")
	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", userAge=" + userAge
				+ ", phone=" + phone + ", email=" + email + ", userGenTime=" + userGenTime + ", userLoginTime="
				+ userLoginTime + ", userLastLoginTime=" + userLastLoginTime + ", userLoginIp=" + userLoginIp
				+ ", userLoginCount=" + userLoginCount + ", deleted=" + deleted + "]";
	}


	@Column(name="user_last_login_ip")
	public String getUserLastLoginIp() {
		return userLastLoginIp;
	}


	public void setUserLastLoginIp(String userLastLoginIp) {
		this.userLastLoginIp = userLastLoginIp;
	}


}