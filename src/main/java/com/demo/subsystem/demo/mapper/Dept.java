package com.demo.subsystem.demo.mapper;

import java.util.List;

import com.demo.subsystem.common.BaseEntity;

public class Dept extends BaseEntity {
	private static final long serialVersionUID = -3718371316465016311L;

	private String deptname;
	private List<User> userList;
	
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
