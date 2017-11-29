package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingtech.web.commons.base.service.ContractService;

@RequestMapping("/loan")
@Controller
public class LoanContractApiController {
	
	@Autowired
	private ContractService contractService;

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
		model.addAttribute("list",contractService.listAll());
		return "/loan/loanEdit";
	}  
}
