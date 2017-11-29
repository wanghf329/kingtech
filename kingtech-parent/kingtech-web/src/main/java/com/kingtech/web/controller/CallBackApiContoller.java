package com.kingtech.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.kingtech.model.AsyReponseModel;
import com.kingtech.web.commons.base.api.PaymentApi;

@Controller
@RequestMapping("/callback")
@Slf4j
public class CallBackApiContoller {
	
	@Autowired
	private PaymentApi paymentApi;
	
	@RequestMapping(method = RequestMethod.POST,value="/result")
	public void callResult(HttpServletRequest request) throws UnsupportedEncodingException, IOException{
		
		BufferedReader	br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) { 
		  sb.append(temp);
		}
		br.close();
		String data  = sb.toString();
		AsyReponseModel reponseModel = JSON.parseObject(data, AsyReponseModel.class);
		log.info("接受到异步回调信息 asyReponseModel ={}",reponseModel);
		log.info("=========>result={},{},{},{},{}",reponseModel.getClientId(),reponseModel.getAppKey(),reponseModel.getResultCode(),reponseModel.getResultMsg(),reponseModel.getRoundStr());
		paymentApi.handleResult(reponseModel);
		
	}

}
