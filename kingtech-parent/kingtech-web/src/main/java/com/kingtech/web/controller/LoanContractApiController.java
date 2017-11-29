package com.kingtech.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/loan")
@Controller
public class LoanContractApiController {

	/**
	 * 使用帮助页面
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/list")
	public String list(Model model) { 
		return "/loan/loanList";
	}  
	
	@RequestMapping(method = RequestMethod.GET,value="/edit")
	public String edit(Model model) { 
		return "/loan/loanEdit";
	}  
}
