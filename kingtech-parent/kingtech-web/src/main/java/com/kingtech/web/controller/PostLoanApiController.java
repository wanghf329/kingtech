package com.kingtech.web.controller;

import java.math.BigDecimal;

<<<<<<< HEAD
import org.apache.commons.lang3.time.DateUtils;
=======
>>>>>>> 7e0a5ef8f0a4f1b52b164b92189d32e8b920fa9f
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

<<<<<<< HEAD
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.web.commons.base.service.ContractService;
import com.kingtech.web.commons.base.service.ExtendRepayService;
=======
import com.kingtech.model.RepayInfoModel;
import com.kingtech.web.commons.base.service.PostLoanService;
>>>>>>> 7e0a5ef8f0a4f1b52b164b92189d32e8b920fa9f

@Controller
@RequestMapping("/postLoan")
public class PostLoanApiController {
	
	@Autowired
<<<<<<< HEAD
	private ContractService contractService;
	
	@Autowired
	private ExtendRepayService extendRepayService;
=======
	private PostLoanService postLoanService;
	
	
>>>>>>> 7e0a5ef8f0a4f1b52b164b92189d32e8b920fa9f

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
		model.addAttribute("contracts", contractService.listAll());
		model.addAttribute("extendRepayList", extendRepayService.listAll());
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
	
	@RequestMapping(method = RequestMethod.POST, value = "extendrepay/edit")
	public String extendrepayEdit(Model model,String id,String loanContractId,long extendNum, 
								  String repayDate,BigDecimal repayAmount, 
								  BigDecimal repayPrincipalAmount,
								  BigDecimal repayInterestAmount) {
		try {
			extendRepayService.addOrEdit(id, loanContractId, extendNum, DateUtils.parseDate(repayDate, "yyyy-MM-dd"), repayAmount, repayPrincipalAmount, repayInterestAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/postLoan/extensionrepayinfo";
	}
	
	/**
	 * 展期还款信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "extendrepay/detail/{id}")
	public RepayExtendInfoModel extendrepayDetail(Model model,@PathVariable("id") String id) {
		RepayExtendInfoModel rf = extendRepayService.getById(id);
		return rf;
	}
}
