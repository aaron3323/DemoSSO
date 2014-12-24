package com.demo.subsystem.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 获取spring注入的bean </p> 
 * <p>使用方式： <bean class="com.demo.subsystem.common.ApplicationContextHolder" /></p>
 * <p>使用方式： ApplicationContextHolder.getBean("MainServiceImpl")</p>
 * <p>Author:aaron</p>
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    private static Log log = LogFactory.getLog(ApplicationContextHolder.class);
    private static ApplicationContext applicationContext;
    
    @SuppressWarnings("all")
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if(this.applicationContext != null) {
            throw new IllegalStateException("ApplicationContextHolder already holded 'applicationContext'.");
        }
        this.applicationContext = context;
        log.info("holded applicationContext,displayName:"+applicationContext.getDisplayName());
    }
     
    public static ApplicationContext getApplicationContext() {
        if(applicationContext == null)
            throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
        return applicationContext;
    }
     
    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }
     
    public static void cleanHolder() {
        applicationContext = null;
    }
}
