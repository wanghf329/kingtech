package com.kingtech.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
