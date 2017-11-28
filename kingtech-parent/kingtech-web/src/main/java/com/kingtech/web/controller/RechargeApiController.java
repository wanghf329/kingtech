package com.kingtech.web.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingtech.model.RechargeRecordModel;
import com.kingtech.service.RechargeRecordService;
import com.kingtech.utils.OTDUtils;

@RequestMapping("/recharge")
@Controller
@Slf4j
public class RechargeApiController {
	
//	@Autowired
	private RechargeRecordService rechargeRecordService;

	/**
	 * 充值列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/list")
	public String list(Model model) { 
		log.info("----------------------------");   
		model.addAttribute("pageResult", rechargeRecordService.listPaging(new PageRequest(0, 10)));
		return "/recharge/list";
	}  
	
	/**
	 * 充值录入
	 * @param model
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.POST,value="/create")
	public String create(Model model,@ModelAttribute RechargeRecordModel rechargeModel) { 
		rechargeRecordService.add(OTDUtils.getRechargeRecord(rechargeModel));
		return "/recharge/create";
	} 	
	
	
}
