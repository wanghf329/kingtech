package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingtech.model.TrafficPriceModel;
import com.kingtech.service.TrafficService;
import com.kingtech.utils.OTDUtils;

@RequestMapping("/traffic")
@Controller
public class TrafficApiController {
	
	@Autowired
	private TrafficService trafficService;

	/**
	 * 使用帮助页面
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/price")
	public String list(Model model) { 
		return "/traffic/price";
	}  
	
	
	@RequestMapping(method = RequestMethod.POST,value="add")
	public String add(Model model,@ModelAttribute TrafficPriceModel trafficePrice) { 
		trafficService.add(OTDUtils.getTrafficPrive(trafficePrice));
		return "/traffic/price";
	}  
}
