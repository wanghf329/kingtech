package com.kingtech.web.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingtech.model.AsyReponseModel;
import com.kingtech.web.commons.base.api.PaymentApi;

@Controller
@RequestMapping("/callback")
@Slf4j
public class CallBackApiContoller {
	
	@Autowired
	private PaymentApi paymentApi;
	
	@RequestMapping(method = RequestMethod.POST,value="/result")
	public void callResult(AsyReponseModel reponseModel){
		
		log.info("接受到异步回调信息 asyReponseModel ={}",reponseModel);
		paymentApi.handleResult(reponseModel);
		
	}

}
