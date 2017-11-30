package com.kingtech.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.web.commons.base.service.BorrowerService;

@RequestMapping("/borrower")
@Controller
public class BorrowerApiController {
	
	@Autowired
	private BorrowerService borrowerService;

	@RequestMapping(method = RequestMethod.GET,value="/corporationList")
	public String corporationList(Model model) { 
		System.out.println("-------------");
		return "borrower/corporationBorrowerList";
	}  
	@RequestMapping(method = RequestMethod.GET, value = "/personList")
	public String personList(Model model){
		return "borrower/personBorrowerList"; 
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="list/S_1")
	public List<EnterpriseCustomer> listByType1(Model model) {
			return borrowerService.listAllEnterpries();
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="list/S_0")
	public List<PersonalCustomer> listByType2(Model model) {
			return borrowerService.listAllPersonal();
	}
}
