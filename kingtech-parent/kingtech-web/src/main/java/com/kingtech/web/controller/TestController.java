package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingtech.enums.IdentifierType;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.http.service.FinanceService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private  FinanceService financeService;
	
	
	@Autowired
	private PaymentApi paymentApi;
	
	@RequestMapping(method = RequestMethod.GET,value="/data")
	public String getData(){
		paymentApi.branchInfoApi("5a846a26-ed18-4147-bc07-dc80d152ca26",IdentifierType.A);
		return null;
	}

}
