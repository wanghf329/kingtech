package com.kingtech.web.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.web.commons.base.service.PostLoanService;

@Controller
@RequestMapping("/postLoan")
public class PostLoanApiController {
	
	@Autowired
	private PostLoanService postLoanService;
	
	

	/**
	 * 还款信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "repayinfo")
	public String repayInfo(Model model) {
		model.addAttribute("contracts", postLoanService.listAllContract());
		model.addAttribute("list", postLoanService.listAllRepayInfo());
		return "/postloan/repayInfo";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getRepayInfo/{id}")
	@ResponseBody
	public RepayInfoModel getRepayInfo(Model model,@PathVariable String id) {
		return postLoanService.getRepayInfoById(id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "add/repayInfo")
	public String addNewRepayInfo(Model model,String id,
								  String repayDate,
								  BigDecimal repayAmount,
								  BigDecimal repayPrincipalAmount,
								  BigDecimal repayInterestAmount,
								  String loanContractId){
		postLoanService.addNewRepayInfo(id, repayDate, repayAmount, repayPrincipalAmount, repayInterestAmount, loanContractId);
		return "redirect:/postLoan/repayinfo";
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
		model.addAttribute("contracts", postLoanService.listAllContract());
		model.addAttribute("list", postLoanService.listAllOtherBaddebt());
		return "/postloan/badDebtsInfo";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "getBaddebtsInfo/{id}")
	@ResponseBody
	public OtherBaddebtModel getBaddebtsInfo(Model model,@PathVariable String id) {
		return postLoanService.getBaddebtInfoById(id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "add/baddebtsInfo")
	public String addNewBaddebtsInfo(Model model,String id,
								    String setDate,
								    BigDecimal badMoney,
								    String  followupWork,
								    String loanContractId){
		postLoanService.addNewBaddebtInfo(id,setDate,badMoney,followupWork, loanContractId);
		return "redirect:/postLoan/baddebtsinfo";
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
