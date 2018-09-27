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
import com.kingtech.dao.entity.FinanceMonthBalance;
import com.kingtech.dao.entity.RepaymentFinance;
import com.kingtech.enums.ChannelTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.FinanceMonthBalanceModel;
import com.kingtech.model.RepaymentFinanceModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.CapitalService;
import com.kingtech.web.commons.base.service.FinanceMonthBalanceService;
import com.kingtech.web.commons.base.service.RepaymentFinanceService;

@RequestMapping("/finance")
@Controller
public class FinanceApiController {
	
	@Autowired
	private CapitalService capitalService;
	
	@Autowired
	private RepaymentFinanceService repaymentFinanceService;
	
	@Autowired
	private FinanceMonthBalanceService financeMonthBalanceService;
	
	
	@RequestMapping(method = RequestMethod.GET,value="/capitalList")
	public String captailList(Model model) { 
		return "/finance/capitalList";
	} 
	
	@RequestMapping(method = RequestMethod.GET,value="/repaymentList")
	public String repaymentList(Model model) { 
		return "/finance/repaymentList";
	} 
	
	@RequestMapping(method = RequestMethod.GET,value="/monthBalanceList")
	public String monthBalanceList(Model model) { 
		return "/finance/monthBalanceList";
	} 
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/capitalList/data")
	public PagedResult<Capital> capitalListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return capitalService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/repaymentList/data")
	public PagedResult<RepaymentFinance> repaymentListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return repaymentFinanceService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/monthBalanceList/data")
	public PagedResult<FinanceMonthBalance> monthBalanceListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return financeMonthBalanceService.pageList(PageInfo.page(firstIndex, pageSize));
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/repayment/edit")
	public String editRepayment(Model model,  String id) {
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("repayment", repaymentFinanceService.getById(id));
		}
		return "/finance/repaymentEdit";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/monthBalance/edit")
	public String editFinanceBalance(Model model,  String id) {
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("monthBalance", financeMonthBalanceService.getById(id));
		}
		return "/finance/monthBalanceEdit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/capital/save")
	public String saveCapital(Model model, CapitalModel capitalModel) {
		capitalService.addNew(capitalModel);
		return "redirect:/finance/monthBalanceList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/repayment/save")
	public String saveRepayment(Model model, RepaymentFinanceModel repayment) {
		repaymentFinanceService.addNew(repayment);
		return "redirect:/finance/repaymentList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/monthBalance/save")
	public String saveFinanceBalance(Model model, FinanceMonthBalanceModel financeBalance) {
		financeMonthBalanceService.addNew(financeBalance);
		return "redirect:/finance/monthBalanceList";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	
	

}
