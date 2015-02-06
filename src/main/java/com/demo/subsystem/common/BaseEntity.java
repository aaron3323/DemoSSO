package com.demo.subsystem.common;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -7983834112540770192L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
