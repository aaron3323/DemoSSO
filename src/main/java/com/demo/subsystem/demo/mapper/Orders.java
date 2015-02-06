package com.demo.subsystem.demo.mapper;

import java.io.Serializable;

import com.demo.subsystem.common.BaseEntity;

public class Orders extends BaseEntity {
	private static final long serialVersionUID = 8586054465638301060L;

	private String name;
	private String userId;
	private User user;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
