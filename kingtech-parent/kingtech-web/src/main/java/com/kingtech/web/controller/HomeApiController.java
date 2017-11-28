package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingtech.web.commons.base.service.FinanceService;

@Controller
public class HomeApiController {


	@Autowired
	FinanceService financeService;
	/**
	 * 使用帮助页面
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="")
	public String dashborad(Model model) { 
		return "/dashborad";
	} 
	
	
	@RequestMapping(method = RequestMethod.GET,value="/hello")
	public String hello(Model model) { 
		
		financeService.inputInstitutionInfo();
		return null;
	}  
}
