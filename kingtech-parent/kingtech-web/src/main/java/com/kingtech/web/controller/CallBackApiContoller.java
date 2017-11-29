package com.kingtech.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public void callResult(HttpServletRequest request,  AsyReponseModel reponseModel){
		
		log.info("clientId ={}",request.getParameter("clientId"));
		log.info("roundStr ={}",request.getParameter("roundStr"));
		log.info("appKey ={}",request.getParameter("appKey"));
		log.info("reqId ={}",request.getParameter("reqId"));
		log.info("resultCode ={}",request.getParameter("resultCode"));
		log.info("resultMsg ={}",request.getParameter("resultMsg"));
		log.info("sign ={}",request.getParameter("sign"));
		log.info("接受到异步回调信息 asyReponseModel ={}",reponseModel);
		paymentApi.handleResult(reponseModel);
		
	}

}
