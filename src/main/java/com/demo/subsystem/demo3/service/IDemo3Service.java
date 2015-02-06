package com.demo.subsystem.demo3.service;

import com.demo.subsystem.common.BaseService;
import com.demo.subsystem.demo3.mapper.UserEntity;

public interface IDemo3Service extends BaseService {
	
	int save(UserEntity userEntity);

}
