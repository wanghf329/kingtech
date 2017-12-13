package com.kingtech.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/postLoan")
public class PostLoanApiController {

	/**
	 * 还款信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "repayinfo")
	public String repayInfo(Model model) {
		return "/postloan/repayInfo";
	}

	/**
	 * 展期还款计划信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "extensionrepayplaninfo")
	public String extensionRepayPlanInfo(Model model) {
		return "/postloan/extensionRepayPlanInfo";
	}

	/**
	 * 展期还款信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "extensionrepayinfo")
	public String extensionRepayInfo(Model model) {
		return "/postloan/extensionRepayInfo";
	}

	/**
	 * 坏账信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "baddebtsinfo")
	public String badDebtsInfo(Model model) {
		return "/postloan/badDebtsInfo";
	}

	/**
	 * 逾期信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "overdueinfo")
	public String overdueInfo(Model model) {
		return "/postloan/overdueInfo";
	}

	/**
	 * 计提信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "provisioninfo")
	public String accruedInfo(Model model) {
		return "/postloan/provisionInfo";
	}
}
