package com.demo.subsystem.demo3.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.demo.subsystem.common.BaseServiceImpl;
import com.demo.subsystem.demo3.mapper.DemoUserMapper;
import com.demo.subsystem.demo3.mapper.UserEntity;
import com.demo.subsystem.demo3.service.IDemo3Service;

@Service("Demo3ServiceImpl")
public class Demo3ServiceImpl extends BaseServiceImpl implements IDemo3Service {
	private static final Log log = LogFactory.getLog(Demo3ServiceImpl.class);
	
	@Override
	public int save(UserEntity userEntity) {
		DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
		int result = demoUserMapper.save(userEntity);
		
		log.debug("saveUser resultNum: "+result);
		
		return result;
	}

}
