package com.demo.subsystem.demo3.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.subsystem.common.BaseController;
import com.demo.subsystem.demo3.service.IDemo3Service;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: demo控制器 </p> 
 * <p>Author:aaron</p>
 */
@Scope("prototype")
@Controller
@RequestMapping(value = "/demo3")
public class Demo3Controller extends BaseController {
	private static final Log log = LogFactory.getLog(Demo3Controller.class);
	
	@Autowired
	@Qualifier("Demo3ServiceImpl")
	private IDemo3Service demoService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Map<String, Object> demo(HttpSession session, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return map;
	}
	
}
