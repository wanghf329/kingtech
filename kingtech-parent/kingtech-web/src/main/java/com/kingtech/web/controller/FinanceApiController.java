package com.kingtech.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.dao.entity.Capital;
import com.kingtech.enums.ChannelTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.CapitalService;

@RequestMapping("/finance")
@Controller
public class FinanceApiController {
	
	@Autowired
	private CapitalService capitalService;
	
	@RequestMapping(method = RequestMethod.GET,value="/capitalList")
	public String captailList(Model model) { 
		return "/finance/capitalList";
	} 
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/capitalList/data")
	public PagedResult<Capital> capitalListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return capitalService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/capital/edit")
	public String editCapital(Model model,  String id) {
		
		model.addAttribute("channels", ChannelTypeEnum.values());
		model.addAttribute("rateTypes", RateTypeEnum.values());
		
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("capital", capitalService.getById(id));
		}
		return "/finance/capitalEdit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/capital/save")
	public String saveCapital(Model model, CapitalModel capitalModel) {
		capitalService.addNew(capitalModel);
		return "redirect:/finance/capitalList";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	
	

}
