package com.demo.subsystem.common;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SelectBuilder;
import org.apache.ibatis.jdbc.SqlBuilder;

public class MapperProvider {
	private static Map<Class, List<PropertyDescriptor>> columnMap = new HashMap<Class, List<PropertyDescriptor>>();
		
	public void caculationColumnList(Object obj) {
		if(columnMap.containsKey(obj.getClass()))
			return;
		
		BeanInfo intro = null;
		try {
			intro = Introspector.getBeanInfo(obj.getClass());
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		PropertyDescriptor[] propertyDescriptors = intro.getPropertyDescriptors();
		List<PropertyDescriptor> columnList = new ArrayList<PropertyDescriptor>(propertyDescriptors.length);
		for (PropertyDescriptor p : propertyDescriptors) {
			Method method = p.getReadMethod();
			if (method.isAnnotationPresent(Column.class)) {
				columnList.add(p);
			}
		}
		columnMap.put(obj.getClass(), columnList);
	}
	
	/**
	 * 用于获取Insert的字段累加
	 * @return
	 * @throws Exception 
	 */
	public static String returnInsertColumnsName(Object obj) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		List<PropertyDescriptor> propertyDescriptorList = columnMap.get(obj.getClass());
		int i = 0;
		
		for(PropertyDescriptor p : propertyDescriptorList) {
			if(i++ != 0) {
				sb.append(',');
			}
			
			sb.append(getPropertyColumnName(p));
		}
		return sb.toString();
	}
	
	public static String getPropertyColumnName(PropertyDescriptor p) {
		String methodName = null;
		Method method = p.getReadMethod();
		if (method.isAnnotationPresent(Column.class)) {
			Annotation columnAnnotation = method.getAnnotation(Column.class);
			if (null != columnAnnotation) {
				Column column = (Column)columnAnnotation;
				String columnName = column.name();
				if (StringUtils.isNotBlank(columnName)) {
					methodName = columnName;
				}
			}
			if (StringUtils.isBlank(methodName)) {
				methodName = p.getName();
			}
		}
		return methodName;
	}	
	
	/**
	 * 用于获取Insert的字段映射累加
	 * @return
	 */
	public String returnInsertColumnsDefine(Object obj) {
		StringBuilder sb = new StringBuilder();

		List<PropertyDescriptor> propertyDescriptorList = columnMap.get(obj.getClass());
		int i = 0;
		for(PropertyDescriptor p : propertyDescriptorList) {
			String column = p.getName();

			Class propertyTypeClass = p.getPropertyType();
			// 不为基本数据类型。为其它的映射bean
			if (!(propertyTypeClass == Byte.class
					|| propertyTypeClass == Character.class
					|| propertyTypeClass == Boolean.class
					|| propertyTypeClass == Integer.class
					|| propertyTypeClass == Long.class
					|| propertyTypeClass == Float.class
					|| propertyTypeClass == Double.class
					|| propertyTypeClass == String.class
					|| propertyTypeClass == Date.class)) {
				try {
					//IdEntity o = (IdEntity)p.getReadMethod().invoke(obj);
					//column +="."+idBeanName(o);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} 
//				catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					e.printStackTrace();
//				}
			}
			
			if(i++ != 0)
				sb.append(',');
			sb.append("#{").append(column).append('}');
		}
		return sb.toString();
	}
	
//	public String SQL() {
//		try {
//			return sql().sql();
//		}finally {
//			RESET();
//		}
//	}
	
//	  private SQL sql() {
//		    SQL sql = localSQL.get();
//		    if (sql == null) {
//		      RESET();
//		      sql = localSQL.get();
//		    }
//		    return sql;
//		  }
	
	public String save(Object record) throws Exception {
		System.out.println("MapperProvider.save");
		//MapperHelper.
		//SqlBuilder.BEGIN();
		//INSERT_INTO(MapperHelper.tablename(record));
		//VALUES(returnInsertColumnsName(record), returnInsertColumnsDefine(record));
		
		return SQL();
	}
	
	

}
