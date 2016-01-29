package com.kingtech.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/about")
@Controller
public class AboutApiController {

	/**
	 * 使用帮助页面
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/help")
	public String list(Model model) { 
		System.out.println("-------------");
		return "/about/help";
	}  
	
	/**
	 * 关于我们
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/about")
	public String about(Model model){
		return "about/about"; 
	}
	
	/**
	 * 注册服务协议文本
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/agreement")
	public String agreement(Model model){
		return "about/agreement";
	}	
}
