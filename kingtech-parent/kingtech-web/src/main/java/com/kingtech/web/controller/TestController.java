package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.web.commons.base.service.FinanceService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private  FinanceService financeService;
	
	
	@RequestMapping(method = RequestMethod.GET,value="/data")
	public String getData(){
		financeService.inputInstitutionInfo();
		return null;
	}

}
