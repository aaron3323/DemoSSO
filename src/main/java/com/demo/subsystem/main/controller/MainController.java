package com.demo.subsystem.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.subsystem.common.ApplicationContextHolder;
import com.demo.subsystem.common.BaseController;
import com.demo.subsystem.main.service.IMainService;

@Scope("prototype")
@Controller
public class MainController extends BaseController {
	
	@Autowired(required=false)
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@Autowired
	@Qualifier("MainServiceImpl")
	private IMainService mainService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String gotoMainPage() {
		System.out.println("注入mainService:"+mainService);
		System.out.println("获取mainService:"+ApplicationContextHolder.getBean("MainServiceImpl"));
//		List<Object> list = sessionRegistry.getAllPrincipals();
//		
//		System.out.println("当前登陆用户数："+list.size());
		
		return "main";
	}

}
