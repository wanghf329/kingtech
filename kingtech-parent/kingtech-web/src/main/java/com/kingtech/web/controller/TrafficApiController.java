package com.kingtech.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/traffic")
@Controller
public class TrafficApiController {

	/**
	 * 使用帮助页面
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/price")
	public String list(Model model) { 
		return "/traffic/price";
	}  
}
