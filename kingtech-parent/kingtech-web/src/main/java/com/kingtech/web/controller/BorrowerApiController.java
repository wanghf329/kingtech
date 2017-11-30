package com.kingtech.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/borrower")
@Controller
public class BorrowerApiController {
	

	@RequestMapping(method = RequestMethod.GET,value="/corporationList")
	public String corporationList(Model model) { 
		System.out.println("-------------");
		return "borrower/corporationBorrowerList";
	}  
	@RequestMapping(method = RequestMethod.GET, value = "/personList")
	public String personList(Model model){
		return "borrower/personBorrowerList"; 
	}
	
//	@ResponseBody
//	@RequestMapping(method = RequestMethod.GET,value="/{type}")
//	public String getById(@PathVariable String loanContractId, Model model) { 
//		model.addAttribute("borrowers_person_list", borrowerService.listAllPersonal());
//		model.addAttribute("borrowers_corp_list", borrowerService.listAllPersonal());
//		return "/loan/loanSupplement";
//	}
}
