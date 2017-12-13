package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.enums.RepayStatusEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.web.commons.base.service.ContractService;
import com.kingtech.web.commons.base.service.RepayExtendPlanService;

@Controller
@RequestMapping("/postLoan")
public class PostLoanApiController {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private RepayExtendPlanService repayExtendPlanService;

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
		model.addAttribute("list", repayExtendPlanService.listAll());
		model.addAttribute("contracts", contractService.listAll());
		model.addAttribute("repayStatus", RepayStatusEnum.values());
		model.addAttribute("overdueFlags", YesNoEnum.values());
		return "/postloan/extensionRepayPlanInfo";
	}
	
	/**
	 * 展期还款计划信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get/extensionrepayplaninfo/{id}", method = RequestMethod.GET)
	public RepayExtendPlanModel getRepayExtendPlan(Model model,@PathVariable("id") String id) {
		return repayExtendPlanService.getById(id);
	}
	
	/**
	 * 展期还款计划信息save
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add/extensionrepayplaninfo", method = RequestMethod.POST)
	public String saveExtensionRepayPlanInfo(Model model,
			String id, String loanContractId, String extendCount, String extendTerm,
			String repayDate, String principal, String returnPrincipal,
			String interest, String returnInterest, String status,
			String overdueFlag, String overdueDays) {
		repayExtendPlanService.addNew(id, loanContractId, extendCount, extendTerm, repayDate, principal, returnPrincipal, 
				interest, returnInterest, status, overdueFlag, overdueDays);
		return "redirect:/postloan/extensionRepayPlanInfo";
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
