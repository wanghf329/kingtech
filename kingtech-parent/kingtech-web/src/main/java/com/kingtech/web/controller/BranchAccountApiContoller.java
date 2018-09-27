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

import com.kingtech.dao.entity.BranchAccountBalance;
import com.kingtech.dao.entity.BranchAccountInfo;
import com.kingtech.enums.AccountStatusEmun;
import com.kingtech.enums.AccountTypeEnum;
import com.kingtech.model.BranchAccountBalanceModel;
import com.kingtech.model.BranchAccountInfoModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.BranchAccountBalanceService;
import com.kingtech.web.commons.base.service.BranchAccountInfoService;

@RequestMapping("/branchAccount")
@Controller
public class BranchAccountApiContoller {
	
	@Autowired
	private BranchAccountInfoService branchAccountInfoService;
	
	@Autowired
	private BranchAccountBalanceService branchAccountBalanceService;
	
	@RequestMapping(method = RequestMethod.GET,value="/accountInfoList")
	public String accountInfoList(Model model) { 
		return "/branchAccount/accountInfoList";
	}  
	
	@RequestMapping(method = RequestMethod.GET,value="/accountBalanceList")
	public String accountBalanceList(Model model) { 
		return "/branchAccount/accountBalanceList";
	}  
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/accountInfoList/data")
	public PagedResult<BranchAccountInfo> accountInfoListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return branchAccountInfoService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/accountBalanceList/data")
	public PagedResult<BranchAccountBalance> accountBalanceListListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return branchAccountBalanceService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/accountInfo/edit")
	public String editAccountInfo(Model model,  String id) {
		
		model.addAttribute("accountTypes", AccountTypeEnum.values());
		model.addAttribute("accountStatuses", AccountStatusEmun.values());
		
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("accountInfo", branchAccountInfoService.getById(id));
		}
		return "/branchAccount/accountInfoEdit";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/accountBalance/edit")
	public String editAccountBalance(Model model,  String id) {
		
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("accountBalance", branchAccountBalanceService.getById(id));
		}
		return "/branchAccount/accountBalanceEdit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/accountInfo/save")
	public String saveAccountInfo(Model model, BranchAccountInfoModel accountInfo) {
		branchAccountInfoService.addNew(accountInfo);
		return "redirect:/branchAccount/accountInfoList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/accountBalance/save")
	public String saveAccountBalance(Model model, BranchAccountBalanceModel accountInfo) {
		branchAccountBalanceService.addNew(accountInfo);
		return "redirect:/branchAccount/accountBalanceList";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
