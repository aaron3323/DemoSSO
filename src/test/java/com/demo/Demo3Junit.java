package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.subsystem.common.Utils;
import com.demo.subsystem.demo3.mapper.UserEntity;
import com.demo.subsystem.demo3.service.IDemo3Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-datasource.xml","classpath:spring-mybatis.xml"})
public class Demo3Junit {
	
	@Autowired
	@Qualifier("Demo3ServiceImpl")
	private IDemo3Service demoService;

	@Test
	public void testSave() {
		try {
			UserEntity userEntity = new UserEntity();
			userEntity.setId(Utils.generateUUID());
			userEntity.setUsername("admin");
			userEntity.setPassword("admin");
			
			int result = demoService.save(userEntity);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
