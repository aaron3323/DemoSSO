package com.demo.subsystem.demo3.mapper;

import java.io.Serializable;

import javax.persistence.Table;

import com.demo.subsystem.common.BaseEntity;

@Table(name="demo3")
public class UserEntity extends BaseEntity {
	private static final long serialVersionUID = -4072716974866756796L;
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
