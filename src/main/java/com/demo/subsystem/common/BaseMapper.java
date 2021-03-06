package com.demo.subsystem.common;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;

import com.demo.subsystem.demo.entity.DemoQueryParam;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: mapper通用接口 </p> 
 * <p>Author:aaron</p>
 * @param <T>
 */
public interface BaseMapper<T extends BaseEntity> {

	@InsertProvider(type = MapperProvider.class, method = "save")
	int save(T t);
	
	int insert(T t);
	int delete(String id);
	int update(T t);
	int count();
	T findById(String id);
	List<T> find(T t);
	List<T> findAll();
	List<T> findPage(DemoQueryParam param);
	
}
