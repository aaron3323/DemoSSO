package com.demo.subsystem.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Table;

public class MapperHelper {
	private final static ThreadLocal<Object> entity = new ThreadLocal<Object>();
	private final static ThreadLocal<String> tablename = new ThreadLocal<String>();
	private final static ThreadLocal<String> columns = new ThreadLocal<String>();
	private final static ThreadLocal<String> values = new ThreadLocal<String>();
	
	public static void setEntity(Object obj) throws Exception {
		entity.set(obj);
		MapperHelper.setTablename(obj);
		//MapperHelper.setColumes(obj);
	}
	
	private static void setTablename(Object obj) throws Exception {
		Table table = obj.getClass().getAnnotation(Table.class);
		if(table != null)
			tablename.set(table.name());
		else
			throw new Exception("undefine POJO @Table, need Tablename(@Table(name))");
	}
	
	private static void setEntityData(Object obj) throws Exception {
		BeanInfo beanInfoCmd = Introspector.getBeanInfo(obj.getClass());
		
		PropertyDescriptor[] propertyDescriptors = beanInfoCmd.getPropertyDescriptors();
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			//属性名--字段名
			String propertyName = descriptor.getName();
			
			Class propertyTypeClass = descriptor.getPropertyType();
		}
	}

	public static String toUrl(Object cmd) {
		String param = null;
		
		try {
			StringBuffer sb = new StringBuffer();
			Class<?> typeCmd = cmd.getClass();
			BeanInfo beanInfoCmd = Introspector.getBeanInfo(typeCmd);

			PropertyDescriptor[] propertyDescriptorsCmd = beanInfoCmd.getPropertyDescriptors();
			TreeMap<String, String> sortMap = new TreeMap<String, String>();
			for(int i = 0; i < propertyDescriptorsCmd.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptorsCmd[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(cmd, new Object[0]);
					if(null != result) {
						if(descriptor.getPropertyType().isAssignableFrom(java.util.List.class)) {
							List<Object> objList = (List<Object>)result;
							int k = 0;
							for(Object obj : objList) {
								Class<?> type = obj.getClass();
								BeanInfo beanInfo = Introspector.getBeanInfo(type);
								PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
								for(int j = 0; j < propertyDescriptors.length; j++) {
									PropertyDescriptor _descriptor = propertyDescriptors[j];
									String _propertyName = _descriptor.getName();
									if(!_propertyName.equalsIgnoreCase("class")) {
										Method _readMethod = _descriptor.getReadMethod();
										Object _result = _readMethod.invoke(obj, new Object[0]);
										if(null == _result) {
											continue;
										}
										String pName = propertyName+"["+k+"]"+"."+_propertyName;
										
										sortMap.put(pName.toLowerCase(), URLEncoder.encode(_result.toString(),"UTF-8").replaceAll("\\+", "%20"));
									}
								}
								k++;
							}
						}else {
							sortMap.put(propertyName.toLowerCase(), URLEncoder.encode(result.toString(),"UTF-8").replaceAll("\\+", "%20"));
						}
					}
				}
			}
			
			for(String key : sortMap.keySet()) {
				String value = sortMap.get(key);
				sb.append(key + "=" + value + "&");
			}
			
			param = sb.toString();
			param = param.substring(0, param.length()-1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return param;
	}
}
